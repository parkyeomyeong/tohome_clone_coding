
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <script type="text/javascript" src="./member.js"></script>
    <script>
      function idcheck() {
        console.log("안되는건가?");
        if (document.formm.id.value == "") {
          alert('아이디를 입력하여 주십시오.');
          document.formm.id.focus();
          return;
        }
        var url = "TohomeServlet?command=id_check_form&id=" + document.formm.id.value;
        window.open( url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=330, height=200");
      }
    </script>
</head>
<body>
<article>
  <h2>Join Us</h2>
  <form id="join" action="TohomeServlet?command=join" method="post" name="formm">
    <fieldset>
      <legend>Basic Info</legend>
      <label>User ID</label>
      <input type="text"      name="id"        size="12"  >
      <input type="hidden"    name="reid">
      <input type="button"    value="중복 체크"  class="dup" onclick="idcheck()"><br>
      <label>Password</label>
      <input type="password"  name="pwd"><br>
      <label>Retype Password</label>
      <input type="password"  name="pwdCheck"><br>
      <label>Name</label>
      <input type="text"      name="name"><br>
      <label>E-Mail</label>
      <input type="text"      name="email"><br>

    </fieldset>
    <fieldset>
      <legend>Optional</legend>
      <label>Zip Code</label>
      <input type="text"       name="zipNum"   size="10" >
      <input type="button"     value="주소 찾기" class="dup" onclick="post_zip()"><br>
      <label>Address</label>
      <input type="text"        name="addr1"   size="50">
      <input type="text"        name="addr2"   size="25"> <br>
      <label>Phone Number</label>
      <input  type="text"       name="phone"><br>
    </fieldset>
    <div class="clear"></div>
    <div id="buttons">
      <input type="button"    value="회원가입"   class="submit" onclick="go_save()">
      <input type="reset"      value="취소"     class="cancel">
    </div>
  </form>
</article>
</body>
</html>
