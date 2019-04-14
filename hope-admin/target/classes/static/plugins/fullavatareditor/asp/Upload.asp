<%@ LANGUAGE = "VBSCRIPT" CODEPAGE = "65001"%>
<%
Response.Charset			= "UTF-8"	'指定输出网页编码
Server.ScriptTimeOut		= 5000		'脚本执行超时最大时限
'温馨提示：
'	在flash的参数名upload_url中可自行定义一些参数（请求方式：POST），定义后在服务器端获取即可，比如可以应用到用户验证，文件的保存名等。
'	如下代码在上传接口Upload.asp中定义了一个user=xxx的参数：
'		var swf = new fullAvatarEditor("swf", {
'			id: "swf",
'			upload_url: "Upload.asp?user=xxx"
'		});
'	在本页即可用Upload.Form("user")获取xxx。
'	本示例未作极致的用户体验与严谨的安全设计（如用户直接访问此页时该如何，万一客户端数据不可信时验证文件的大小、类型等），只保证正常情况下无误，请阁下注意。
'艾恩ASP无组件上传类 V13.01.16
%>
<!--#include file="Upload.asp.cls"-->
<%
'生成指定长度的随机码。
Function CreateRandomCode(ByVal Length)
	Randomize
	Dim RandCode		: RandCode = ""
	Dim RandChar		: RandChar = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
	Dim RandCharArray	: RandCharArray = Split(RandChar, ",")
	Dim i
	For i = 1 To Length
		RandCode = RandCode & RandCharArray(Int(36 * Rnd))
	Next
	CreateRandomCode = RandCode
End Function

Function GetVirtualPath(ByVal path)
	Dim webRoot : webRoot = Server.MapPath("/")
	Dim physicalPath : physicalPath = Server.MapPath(path)
	GetVirtualPath = Replace(physicalPath, webRoot, "")
	GetVirtualPath = Replace(GetVirtualPath, "\", "/")
End Function

'取服务器时间+8位随机码作为部分文件名，确保文件名无重复。
Dim FileName : FileName = Year(NowTime) & Right("0" & Month(NowTime), 2) & Right("0" & Day(NowTime), 2) & Right("0" & Hour(NowTime), 2) & Right("0" & Minute(NowTime), 2) & Right("0" & Second(NowTime), 2) & Left(Replace(Right(timer(), 2), ".", "") & "0", 2) & CreateRandomCode(8)

Dim Msg								'表示自定义的附加返回消息。
Dim Success : Success = "false"		'表示图片是否已上传成功。
Dim SavePath : SavePath = "upload"	'保存在服务器的虚拟路径
Dim SourceUrl						'表示原始图片的保存地址。
DIM AvatarUrls						'表示所有头像图片的保存地址，多个之间使用英文的逗号“,”分隔。

Dim SuccessNum : SuccessNum = 0

On Error Resume Next
'建立上传对象
Dim Upload
Set Upload = new AnUpLoad
	Upload.Charset = "UTF-8"				'设置字符集
	Upload.Exe = "*.bmp;*.jpg;*.gif;*.png;"	'设置允许上传的文件类型
	Upload.SingleSize = 1024 * 1024 * 2		'设置单个文件的最大上传限制，按字节计算，该处为2MB
	Upload.GetData()
	If Upload.ErrorID > 0 Then
		Msg = Upload.Description
	Else
		'定义一个变量用以储存当前头像的序号
		Dim AvatarNumber : AvatarNumber = 1
		Dim i : i = 1
		'遍历所有文件域
		For Each F In Upload.Files(-1)
			Dim File
			Set File = Upload.Files(F)
				If File.IsFile Then
					'如果 file 域的名称是以__source（可在插件配置参数中自定义，参数名：src_field_name）打头，说明是原始图片，如果在插件中定义可以上传的话，可在此处理。
					If InStr(File.FormName, "__source") = 1 Then
						'保存的文件名，不包括扩展名
						File.UserSetName = "asp_source_" & FileName
						'保存的文件扩展名，不含点“.”
						File.Extend = "jpg"
						'当前头像基于原图的初始化参数（即只有上传原图时才会发送该数据），用于修改头像时保证界面的视图跟保存头像时一致，提升用户体验度。
						'修改头像时设置默认加载的原图url为当前原图url+该参数即可，可直接附加到原图url中储存，不影响图片呈现。
						Dim InitParams : InitParams = Upload.Forms("__initParams")
						SourceUrl = GetVirtualPath(SavePath & "/" & File.UserSetName & "." & File.Extend) & InitParams
						If File.SaveToFile(SavePath, -1, True) Then
							SuccessNum = SuccessNum + 1
						Else
							Msg = "原图片保存失败，错误信息：" & File.Exception	'错误信息请参考同目录中的PDF文件
						End If
					'否则就是头像图片(file 域的名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names)
					Else
						'保存的文件名，不包括扩展名
						File.UserSetName = "asp_avatar" & AvatarNumber & "_" & FileName
						'保存的文件扩展名，不含点“.”
						File.Extend = "jpg"
						If File.SaveToFile(SavePath, -1, True) Then
							SuccessNum = SuccessNum + 1
							AvatarUrls = AvatarUrls & """" & GetVirtualPath(SavePath & "/" & File.FileName & "." & File.Extend) & ""","
							AvatarNumber = AvatarNumber + 1
						Else
							Msg = "头像保存失败，错误信息：" & File.Exception	'错误信息请参考同目录中的PDF文件
						End If
					End If
				Set File = Nothing
			End If
		Next
	End If
'释放上传对象
Set Upload = Nothing

If Err.Number <> 0 Then Err.Clear

If SuccessNum > 0 Then
	Success = "true"
End If

If AvatarUrls <> "" Then
	AvatarUrls = Left(AvatarUrls, Len(AvatarUrls) - 1)
End If
'返回上传结果(json)
Response.Write "{""success"":" & Success & ", ""msg"":""" & Msg & """, ""sourceUrl"":""" & SourceUrl & """, ""avatarUrls"":[" & AvatarUrls & "]}"
%>
