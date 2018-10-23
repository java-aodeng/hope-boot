using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using System.Web;
using Newtonsoft.Json;
/*
温馨提示：
	在flash的参数名upload_url中可自行定义一些参数（请求方式：POST），定义后在服务器端获取即可，比如可以应用到用户验证，文件的保存名等。
	本示例未作极致的用户体验与严谨的安全设计（如用户直接访问此页时该如何，万一客户端数据不可信时验证文件的大小、类型等），只保证正常情况下无误，请阁下注意。
*/
public partial class Upload : System.Web.UI.Page
{
	protected void Page_Load(object sender, EventArgs e)
	{
		Result result = new Result();
		result.avatarUrls = new ArrayList();
		result.success = false;
		result.msg = "Failure!";
		//取服务器时间+8位随机码作为部分文件名，确保文件名无重复。
		string fileName = DateTime.Now.ToString("yyyyMMddhhmmssff") + CreateRandomCode(8);
		//定义一个变量用以储存当前头像的序号
		int avatarNumber = 1;
		//遍历所有文件域
		foreach(string fieldName in Request.Files.AllKeys)
		{
			HttpPostedFile file = Request.Files[fieldName];
			//处理原始图片（默认的 file 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）
			//如果在插件中定义可以上传原始图片的话，可在此处理，否则可以忽略。
			if(fieldName == "__source")
			{
				//文件名，如果是本地或网络图片为原始文件名（不含扩展名）、如果是摄像头拍照则为 *FromWebcam
				//fileName = file.FileName;
				//当前头像基于原图的初始化参数（即只有上传原图时才会发送该数据），用于修改头像时保证界面的视图跟保存头像时一致，提升用户体验度。
				//修改头像时设置默认加载的原图url为当前原图url+该参数即可，可直接附加到原图url中储存，不影响图片呈现。
				string initParams = Request.Form["__initParams"];
				result.sourceUrl = string.Format("upload/csharp_source_{0}.jpg", fileName);
				file.SaveAs(Server.MapPath(result.sourceUrl));
				result.sourceUrl += initParams;
				/*
					可在此将 result.sourceUrl 储存到数据库，如果有需要的话。
				*/
			}
			//处理头像图片(默认的 file 域的名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names)
			else if (fieldName.StartsWith("__avatar"))
			{
				string virtualPath = string.Format("upload/csharp_avatar{0}_{1}.jpg", avatarNumber, fileName);
				result.avatarUrls.Add(virtualPath);
				file.SaveAs(Server.MapPath(virtualPath));
				/*
					可在此将 virtualPath 储存到数据库，如果有需要的话。
				*/
				avatarNumber++;
			}
			/*
			else
			{
				如下代码在上传接口Upload.aspx中定义了一个user=xxx的参数：
				var swf = new fullAvatarEditor('swf', {
					id: 'swf',
					upload_url: 'Upload.aspx?user=xxx'
				});
				在此即可用Request.Form["user"]获取xxx。
			}
			*/
		}
		result.success = true;
		result.msg = "Success!";
		//返回图片的保存结果（返回内容为json字符串，可自行构造，该处使用Newtonsoft.Json构造）
		Response.Write(JsonConvert.SerializeObject(result));
	}
	/// <summary>
	/// 生成指定长度的随机码。
	/// </summary>
	private string CreateRandomCode(int length)
	{
		string [] codes = new string [36] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		StringBuilder randomCode = new StringBuilder();
		Random rand = new Random();
		for ( int i =0; i < length; i++ )
		{
			randomCode.Append(codes [rand.Next(codes.Length)]);
		}
		return randomCode.ToString();
	}
	/// <summary>
	/// 表示图片的上传结果。
	/// </summary>
	private struct Result
	{
		/// <summary>
		/// 表示图片是否已上传成功。
		/// </summary>
		public bool success;
		/// <summary>
		/// 自定义的附加消息。
		/// </summary>
		public string msg;
		/// <summary>
		/// 表示原始图片的保存地址。
		/// </summary>
		public string sourceUrl;
		/// <summary>
		/// 表示所有头像图片的保存地址，该变量为一个数组。
		/// </summary>
		public ArrayList avatarUrls;
	}
}
