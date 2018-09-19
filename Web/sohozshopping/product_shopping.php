<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 $product_id = $_POST['product_id'];
 $product_name = $_POST['product_name'];
 $product_price = $_POST['product_price'];
 $username = $_POST['username'];
 $active = 0;

 require_once('dbConnect.php');
 
 $sql = "INSERT INTO customer_shopping (product_id,product_name,product_price,confirm,username) VALUES ('$product_id','$product_name','$product_price','$active','$username')";
 if(mysqli_query($con,$sql)){
 echo 'successfully added';
 }else{
 echo 'oops! Please try again!';
 }
}
else{
echo 'error';
}
?>