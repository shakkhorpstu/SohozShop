<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
require_once('dbConnect.php');
 $new_password = $_POST['new_password'];
 
$sql = "UPDATE user_registration SET password = '$new_password'";
//$sql = "INSERT INTO user_registration (first_name,last_name,username,city,full_address,password) VALUES ('$firstname','$lastname','$user_name','$u_city','$u_address','$u_password')";
if(mysqli_query($con,$sql)){
echo 'successfully changed';
}else{
echo 'oops! Please try again!';
}
}
?>