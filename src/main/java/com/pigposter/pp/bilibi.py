import os
import asyncio
import sys
from bilibili_api import video, Credential
# 一共修改五个地方
# cookie里提取SESSDATA,BILI_JCT,BUVID3
# 详见https://www.moyu.moe/bilibili-api/#/get-credential
# 然后设置视频文件路径和封面图片路径

SESSDATA = "56b7df4e%2C1642526443%2C420d6%2A71"
BILI_JCT = "1067c34db36bea9fa697a5da2040d8c2"
BUVID3 = "ADF78293-84EC-4F07-84FD-FE7E4E6944FD143078infoc"

credential = Credential(sessdata=SESSDATA, bili_jct=BILI_JCT, buvid3=BUVID3)

async def main():
  # 视频文件路径
#   video_path = r"E:\pigposter\pp\src\main\resources\static\img\aaa.mp4"
  video_path = sys.argv[1]
  # 封面图片路径
  cover_path = r"C:\Users\11234\Desktop\vscbg.png"

  # 获取文件名和后缀
  filename, ext = os.path.basename(video_path).split(".")

  # 实例化 P1 对象
  p1 = video.VideoUploaderPageObject(video_stream=open(video_path, "rb"), title=filename, video_format=ext)

  # 视频上传配置
  config = {
    "copyright": 1, #"1 自制，2 转载。",
    "source": "", #"str, 视频来源。投稿类型为转载时注明来源，为原创时为空。",
    "desc": "", #"str, 视频简介。",
    "desc_format_id": 0,
    "dynamic": "", #"str, 动态信息。",
    "interactive": 0,
    "open_elec": 0, #"int, 是否展示充电信息。1 为是，0 为否。",
    "no_reprint": 1, #"int, 显示未经作者授权禁止转载，仅当为原创视频时有效。1 为启用，0 为关闭。",
    "subtitles": {
      "lan": "", #"字幕语言，不清楚作用请将该项设置为空",
      "open": 0
    },
    "tag": "学习,测试", #"str, 视频标签。使用英文半角逗号分隔的标签组。示例：标签1,标签2,标签3",
    "tid": 208, #"int, 分区ID。可以使用 channel 模块进行查询。",
    "title": sys.argv[1], #"视频标题",
    "up_close_danmaku": False, #"bool, 是否关闭弹幕。",
    "up_close_reply": False, #"bool, 是否关闭评论。",
  }

  # 要上传的所有分 P 列表
  pages = [p1]

  # 初始化上传
  uploader = video.VideoUploader(cover=open(cover_path, 'rb'), cover_type="jpg", pages=pages, config=config, credential=credential)

  # 开始上传
  res = await uploader.start()

  # 打印结果（返回的为 bv 号和 av 号）
  print(res)

if __name__ == '__main__':
  # 主入口
  asyncio.get_event_loop().run_until_complete(main())