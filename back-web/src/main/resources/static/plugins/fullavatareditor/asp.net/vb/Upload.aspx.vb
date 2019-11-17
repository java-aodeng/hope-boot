REM 温馨提示：
REM	在flash的参数名upload_url中可自行定义一些参数（请求方式：POST），定义后在服务器端获取即可，比如可以应用到用户验证，文件的保存名等。
REM	本示例未作极致的用户体验与严谨的安全设计（如用户直接访问此页时该如何，万一客户端数据不可信时验证文件的大小、类型等），只保证正常情况下无误，请阁下注意。
Imports Newtonsoft.Json
Partial Class Upload
	Inherits System.Web.UI.Page

	Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs)
		Dim UploadResult As Result = New Result()
		UploadResult.avatarUrls = New ArrayList()
		UploadResult.msg = "Failure!"
		UploadResult.sourceUrl = String.Empty
		UploadResult.success = False
		REM 取服务器时间+8位随机码作为部分文件名，确保文件名无重复。
		Dim FileName As String = Now.ToString("yyyyMMddhhmmssff") & CreateRandomCode(8)
		REM 定义一个变量用以储存当前头像的序号
		Dim AvatarNumber As Integer = 1
		Dim FieldName As String
		For Each FieldName In Request.Files.AllKeys
			Dim File As HttpPostedFile = Request.Files(FieldName)
			REM 处理原始图片（默认的 file 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）
			REM 如果在插件中定义可以上传原始图片的话，可在此处理，否则可以忽略。
			If FieldName = "__source" Then
				REM 文件名，如果是本地或网络图片为原始文件名（不含扩展名）、如果是摄像头拍照则为 *FromWebcam
				REM FileName = file.FileName;
				REM 当前头像基于原图的初始化参数（即只有上传原图时才会发送该数据），用于修改头像时保证界面的视图跟保存头像时一致，提升用户体验度。
				REM 修改头像时设置默认加载的原图url为当前原图url+该参数即可，可直接附加到原图url中储存，不影响图片呈现。
				Dim InitParams As String = Request.Form("__initParams")
				UploadResult.sourceUrl = String.Format("upload/vb_source_{0}.jpg", FieldName)
				File.SaveAs(Server.MapPath(UploadResult.sourceUrl))
				UploadResult.sourceUrl = UploadResult.sourceUrl & InitParams
				REM 可在此将 UploadResult.sourceUrl 储存到数据库，如果有需要的话
			REM 处理头像图片(默认的 file 域的名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names)
			Else If fieldName.StartsWith("__avatar") Then
				Dim VirtualPath As String = String.Format("upload/vb_avatar{0}_{1}.jpg", AvatarNumber, FileName)
				UploadResult.avatarUrls.Add(VirtualPath)
				File.SaveAs(Server.MapPath(VirtualPath))
				AvatarNumber = AvatarNumber + 1
			End If
		REM	Else
			REM	如下代码在上传接口Upload.aspx中定义了一个user=xxx的参数：
			REM		var swf = new fullAvatarEditor('swf', {
			REM			id: 'swf',
			REM			upload_url: 'Upload.aspx?user=xxx'
			REM		});
			REM	在此即可用Request.Form("user")获取xxx。
		REM	End If
		Next
		UploadResult.success = True
		UploadResult.msg = "Success!"
		'返回图片的保存结果（返回内容为json字符串，可自行构造，该处使用Newtonsoft.Json构造）
		Response.Write(JsonConvert.SerializeObject(UploadResult))
	End Sub

	'生成指定长度的随机码。
	Private Function CreateRandomCode(ByVal Length As Integer) As String
		Randomize()
		Dim RandCode As String		= RandCode = String.Empty
		Dim RandChar As String		= "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
		Dim RandCharArray As Array	= Split(RandChar, ",")
		Dim i As Integer
		For i = 1 To Length
			RandCode = RandCode & RandCharArray(Int(36 * Rnd()))
		Next
		CreateRandomCode = RandCode
	End Function

	REM 表示上传结果
	Private Structure Result
		REM 表示图片是否已上传成功。
		Public success As String
		REM 自定义的附加消息。
		Public msg As String
		REM 表示原始图片的保存地址。
		Public sourceUrl As String
		REM 表示所有头像图片的保存地址，该变量为一个数组。
		Public avatarUrls As ArrayList
	End Structure

End Class
