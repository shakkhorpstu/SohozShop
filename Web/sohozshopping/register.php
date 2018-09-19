<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 $firstname = $_POST['first_name'];
 $lastname = $_POST['last_name'];
 $user_name = $_POST['username'];
 $u_city = $_POST['city'];
 $u_address = $_POST['full_address'];
 $u_password = $_POST['password'];
 
 if($firstname == '' || $lastname == '' || $user_name == '' || $u_city == '' || $u_address == '' || $u_password == ''){
 echo 'please fill all values';
 }else{
 require_once('dbConnect.php');
$sql = "SELECT * FROM user_registration WHERE username='$user_name'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 if(isset($check)){
 echo 'username already exist';
 }else{ 
 $sql = "INSERT INTO user_registration (first_name,last_name,username,city,full_address,password) VALUES ('$firstname','$lastname','$user_name','$u_city','$u_address','$u_password')";
 if(mysqli_query($con,$sql)){
 echo 'successfully registered';
 }else{
 echo 'oops! Please try again!';
 }
 }
 mysqli_close($con);
 }
}else{
echo 'error';
}
?>