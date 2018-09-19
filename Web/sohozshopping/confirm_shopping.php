<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

$username = $_POST['username'];

require_once('dbConnect.php');

$sql = "UPDATE customer_shopping SET confirm = 1 WHERE username='$username'";
mysqli_query($con,$sql);
echo "Confirmed";
}
else{
echo "Error";
}

?>
