
#考试人证合一终端人证识别接口服务程序 V1.0.20180417v1

> 本文明确 终端桌面应用程序 向 人证识别接口服务程序 请求的数据接口

- windows 7 64bit
- JDK 8 64bit
- tomcat 8 service.bat
- MySql 5.7
- jFinal
- 环境变量设置程序

## 环境变量
%EXAM_TERMINAL% c:/exam-terminal/attach  

## 安装目录

| 软件 | 端口 | 位置 |
| :--- | :---- | :---- |
| tomcat8 64bit | 8080、8009 | c:/exam-terminal/tomcat8_x64 |
| JDK 8 64bit | - | c:/exam-terminal/tomcat8_x64/jdk |
| 人证识别接口服务程序 | - | c:/exam-terminal/tomcat8_x64/webapps/ROOT |
| 人像对比dll | - | c:/exam-terminal/lib_x64/identify |

## todolist
```
- [ ] 11、测试接口-连接测试 /test/connect
- [ ] 12、测试接口-数据库 /test/db
- [ ] 13、测试接口-认识接口测试 /test/readcard
- [ ] 21、接口服务信息 /api/service/info
- [ ] 31、终端统一认证接口 /api/exam/identify   
- [ ] 32、新増离线考试 /api/exam/addOffline
- [ ] 33、得到所有考试 /api/exam/list
- [ ] 34、得到参加考试人数 /api/exam/statistics   
- [ ] 35、得到参与识别列表 /api/exam/identifyList   
```

## 定义常量

| 常量 | 值 | 说明 |
| :--- | :---- | :---- |
| aes-256-ecb.key | N4FsvOYbiuHIK6C7J3yGuhJ0ZC0FgxwU | 请求的加密密钥，公钥，必需的32位加密key |

## 注意事项
- 所有测试接口均会被请求五次  
- 机器码(sid)为终端唯一识别码(但也有重复的风险) 在注册表中：HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Cryptography

## 默认  
接口请求都需要验证token是否正确，验证规则    
Request:

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| version | String | 终端版本号 |
| token | String | 验证token |
| time | Integer | 请求时间截 |

Response:  

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| state | Integer | 消息状态 |
| msg | String | 消息内容 |

## 已定义的消息返回码
| state | msg | 说明 |
| :--- | :---- | :---- |
| 0 | 成功 | 请求正确执行并正常返回 |
| 10 | 请求验证失败 | 默认的请求参数不完成 |
| 11 | 请求验证失败 | 机器码错误 |
| 12 | 请求验证失败 | 不在时间截允许的时间内 |

## 11、测试接口-连接测试 /api/test/connect  ∨  
Request:

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| index | Integer | 请求顺序号 |
| hope | Integer | 期望值，1-100随机数 |

Response:  

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| data.index | Integer | 请求顺序号 |
| data.hopeValue | Integer | 计算后的值，index*  hope |

## 12、测试接口-数据库 /api/test/db  ∨
测试连接数据库  

## 13、测试接口-认识接口测试 /api/test/readcard    ∨  
对比两组固定照片，一组固定成功结果，一组固定失败结果，如果均为期望结果，返回正确   

## 21、接口服务信息 /api/service/info   ∨  
Response:  

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| data.api.version | String | 接口程序版本 V1.0.20180417v1 |
| data.api.durationTime | String | 识别程序运行时间 时：分：秒 |
| data.api.startTime | String | 识别程序开始运行时间 年-月-日 时：分：秒 |
| data.api.company | String | 公司名称 |
| data.api.author | String | 维护人 |
| data.api.contact | String | 联系手机 |
| data.exam.passScore | Integer | 识别结果及格默认值 |
| data.exam.clearTime | String | 上次清空考试库时间 |
| data.exam.furthestExamId | String | 距离最远的考试id |
| data.exam.furthestExamName | String | 距离最远的考试名称 |
| data.exam.furthestExamTime | String | 距离最远的考试时间 |
| data.identify.version | String | 识别程序dll版本 |
| data.identify.passScore | Integer | 识别结果及格值 |
| data.identify.total | Integer | 识别记录数 |
| data.identify.clearTime | String | 上次清空识别库时间 |

## 31、终端统一认证接口 /api/exam/identify     ∨  
Request:

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| examId | *String | 考试ID |
| examRoomId | String | 考试室ID |
| name | *String | 身份证的姓名 |
| sex | *String | 身份证的民族 |
| idNumber | *String | 身份证的号码 |
| issuedData | String | 身份证的签发日期 |
| validDate | String | 身份证的过期日期 |
| idImgBASE64 | *String | 身份证的相片.jpg格式的BASE64串 |
| cameraImgBASE64 | *String | 摄像头的相片.jpg格式的BASE64串 |

Response:  

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| data.score | Integer | 本次识别分数(重新计算*1) |
| data.rawScore | Number | 本次识别分数(接口的真实值) |
| data.passScore | Integer | 本次识别及格分数(默认是60) |
| data.isPass | Integer | 识别结果 1,通过 0,不通过 |
| data.idNumber | String | 身份证的号码 |
| data.duration | Integer | 识别操作运行时间，单位毫秒 |
| statistics | Object | 统计 |
| statistics.total | Integer | 参加总人数 |
| statistics.ideTotal | Integer | 识别总人数 |
| statistics.ideTime | Integer | 识别总人次 |
| statistics.idePassTotal | Integer | 识别通过人数 |
| statistics.idePassTime | Integer | 识别通过人次 |
| statistics.ideFailTotal | Integer | 识别失败人数 |
| statistics.ideFailTime | Integer | 识别失败人次 |

> *1 计算方式  
大于及格分数时 为 60 + (rawScore-及格分数)/(最高分-及格分数) * 40  
小于及格分数时 为 60 * (rawScore-及格分数)/(最低分-及格分数)  
等于及格分数时 为 60

## 32、新増离线考试 /api/exam/addOffline
V1.0 版本暂时不添加考试人员  
Request:

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| name | String | 考试名称 |
| examTime | String | 考试时间 |
| createDate | String | 创建时间 |
| updateDate | String | 更新时间 |

## 33、得到所有考试 /api/exam/list  ∨
按创建时间倒序  
Response:  

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| data.list | Array | 数组 |
| data.list[].id | String | 考试ID |
| data.list[].name | String | 考试名称 |
| data.list[].examTime | String | 考试时间 |
| data.list[].createDate | String | 创建时间 |
| data.list[].updateDate | String | 更新时间 |

## 34、得到参加考试人数 /api/exam/statistics  ∨ 
Request:

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| examId | *String | 考试ID |

Response:  

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| total | Integer | 参加总人数 |
| ideTotal | Integer | 识别总人数 |
| ideTime | Integer | 识别总人次 |
| idePassTotal | Integer | 识别通过人数 |
| idePassTime | Integer | 识别通过人次 |
| ideFailTotal | Integer | 识别失败人数 |
| ideFailTime | Integer | 识别失败人次 |

## 35、得到参与识别列表 /api/exam/identifyList   
Request:

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| examId | *String | 考试ID |

Response:  

| 参数 | 类型 | 说明 |
| :--- | :---- | :---- |
| data.identify | Array | 数组 |
| data.identify[].id | String | id |
| data.identify[].score | Number | 本次识别分数(重新计算*1) |
| data.identify[].rawScore | Number | 本次识别分数 |
| data.identify[].passScore | Integer | 本次识别及格分数 |
| data.identify[].isPass | Integer | 识别结果 1,通过 0,不通过 |
| data.identify[].idCard.name | String | 身份证的姓名 |
| data.identify[].idCard.nation | String | 身份证的民族 |
| data.identify[].idCard.birthday | String | 身份证的出生日期 |
| data.identify[].idCard.address | String | 身份证的住址 |
| data.identify[].idCard.idNumber | String | 身份证的号码 |
| data.identify[].idCard.issued | String | 身份证的签发机关 |
| data.identify[].idCard.issuedData | String | 身份证的签发日期 |
| data.identify[].idCard.validDate | String | 身份证的过期日期 |
| data.identify[].idCard.idImgPath | String | 身份证相片附件路径 |
| data.identify[].cameraImgPath | String | 摄像头的相片附件路径 |
| data.statistics | Object | 统计 |
| data.statistics.total | Integer | 参加总人数 |
| data.statistics.ideTotal | Integer | 识别总人数 |
| data.statistics.ideTime | Integer | 识别总人次 |
| data.statistics.idePassTotal | Integer | 识别通过人数 |
| data.statistics.idePassTime | Integer | 识别通过人次 |
| data.statistics.ideFailTotal | Integer | 识别失败人数 |
| data.statistics.ideFailTime | Integer | 识别失败人次 |
