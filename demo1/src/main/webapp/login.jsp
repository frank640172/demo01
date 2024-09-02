<%--
  Created by IntelliJ IDEA.
  User: yanbo
  Date: 2024/8/25
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/jquery-3.3.1-min.js"></script>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form>
      <input type="text" id="username"  placeholder="用户名">
    <span id="s_username"></span>
    <br>
      <input type="text" id="password"  placeholder="密码"><br>
      <input  type="submit" value="注册"><br>
</form>

<script>
    
     $(function(){
         $("#username").blur(function () {
              //获取username文本输入框的值
             var username = $(this).val();
             //获取之后调用ajax方法，去查询(即访问servelet看结果)
             $.ajax({
                 url:"login",
                 type:"GET",

                 dataType: "json",
                 data:{
                     username:username
                 },
                 // 接受返回的结果

                 success: function (data) {
                     let v_span = $("#s_username");
                     v_span.css("color","red");
                     console.log(1111111);
                    console.log(data);
                     v_span.html(data.msg);
                 },
                 error: function (data) {
                     let v_span = $("#s_username");
                     v_span.css("color","green");
                    console.log("222222222222222");
                     v_span.val=data.msg;
                 }
                 });
         });
     });
</script>
</body>
</html>
