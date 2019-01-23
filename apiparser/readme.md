APIParser:为点播的大部分用户接口提供一个通用的使用页面以供测试客户使用情况.(点播直播的加密算法和sign位数不同,日后应加上域名判断点/直播后执行不同sign拼接)

        核心类逻辑:
        -ApiParser类:1.总共有多少个API类可以写在properties中来读取(util.GetAllAPI已完成),自动化switch配置
                     2.反射读取所有变量,方法名
                     3.与状态类ParamStatus一起交给MemberHandler,MemberHandler返回拿到的reqParam,同时初始化好ParamStatus
                     4.与状态类MethodStatus一起交给MethodHandler,MethodHandler返回拿到的reqParam,同时初始化好MethodStatus
                     5.根据状态进行:   -计算Sign 和 计算重载的方法的参数         其中根据标记令哪些参数用默认值,哪些参数用传参(亦可批量去
                     properties读取  ---若采用properties则需要syso/resp.write提示一次会用到哪些参数)
                     6.根据 是 POST 还是 GET,调用setGET()/setPOST()       其中setGET是替换参数  setPost是将参数丢入entity
                     -发起请求
                     
        -PropertiesParser类:   1.与APIParser大致相同
                               2.用PropertiesStatus代替了MethodStatus和ParamStatus,对field与method的处理变成了文本的分割和处理
                               3.urlForGet生成更加智能,根据用户/使用者的输入自动生成待占位符的url,根据参数自动替换和判断是否参与sign拼接计算.




        使用方式:1.类反射读取:
                      优点在于若有参数存在设想之外的计算拼接,写一个参数同名函数里面计算return即可;正常使用新建一次接口代码量大约5行,日后易于维护.
                      缺点在于不能即时读取,要么新建接口后重启一次服务,要么实时class.forName(),用代码规约的注释来说就是一种魔法操作.
                      
                      使用示例:
                      //类名 接口url 请求用到的参数 是否需要sign的空函数
                      public class API_GetById {
                          private String urlForGet = "http://v.polyv.net/uc/services/rest?method=getById&vid={vid}&readtoken={readtoken}";
                          private boolean useDefaultValue = true;
                          private String vid = "7ca55a3c6fb1f445d9ab845be127b10b_7";
                          private String readtoken = "66d670ea-c227-42dd-ac99-dd7dad85d23f";

                          private void NoNeedForSign(){}

                          //getter setter
                      }

                
                2.文本/properties/数据库方式存储读取:
                        优点在于简易方便,使用和新建最多就填用户信息即可.虽然不能进行函数重载,但是点播的绝大部分接口都不需要这个功能.
                        缺点在于说白了只是个文本解析器,便捷但不灵活,只能自动计算预想之内会出现的ptime,sign等值,日后添加具体功能并不能针对单个接口,只能整体去实现这个功能.
                        
                        使用示例:http://39.108.65.230:8080/api
                        复制官网帮助文档里面的url 需要的参数 填入使用即可
