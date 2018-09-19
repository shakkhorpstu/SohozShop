<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 $product_id = $_POST['product_id'];
 $username = $_POST['username'];
 
 if($product_id == ''){
 echo 'Something Wrong';
 }else{
 require_once('dbConnect.php'); 
 $sql = "DELETE FROM customer_shopping WHERE product_id = '$product_id' AND username = '$username'";
 if(mysqli_query($con,$sql)){
 echo 'Successfully Removed';
 }
 else{
 echo 'Oops! Please try again!';
 }
 mysqli_close($con);
 }
}else{
echo 'Error';
}
?>