<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#loginpanel {
position:absolute;
left:300px;
top:200px;
}
</style>
</head>
<body>
<?php
require_once __DIR__.'/../controller/InputValidator.php';
require_once __DIR__.'/../model/Equipment.php';
require_once __DIR__.'/../model/Expense.php';
require_once __DIR__.'/../model/Lab.php';
require_once __DIR__.'/../model/Staff.php';
require_once __DIR__.'/../model/Supply.php';
require_once __DIR__.'/../model/URLMS.php';
require_once __DIR__.'/../persistence/PersistenceLabManagement.php';
session_start();
if (isset ( $_SESSION ['errorItem'] ) && ! empty ( $_SESSION ['errorItem'] )) 
{
    echo " * " . $_SESSION ["errorItem"];
}
?>
<form name="form1" method="post" action="checklogin.php">
<div id="loginpanel">
<img src="../imgsrc/321-1.jpg" style="width:480px;height:270px;"></img><br/>
Username&nbsp;&nbsp;<input  type="text" id="username"></input><br/>
<br/>
Password&nbsp;&nbsp;<input  type="password" id="password"></input><br/>
<br/>
<input type="submit" value="login" ></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type ="button" value="New Account" onclick="window.location.href='register.php'"></input>
</div>
</form>
</body>
</html>