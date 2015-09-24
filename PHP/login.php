<?php
define('HOST',127.0.0.1');
 define('USER','techgame');
 define('PASS','Q12we34r!@');
 define('DB','evil');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$username = $_POST['username'];
$password = $_POST['password'];
 
$sql = "select * from user where username='$username' and password='$password'";
 
$res = mysqli_query($con,$sql);
 
$check = mysqli_fetch_array($res);
 
if(isset($check)){
echo 'success';
}else{
echo 'failure';
}
 
mysqli_close($con);
?>