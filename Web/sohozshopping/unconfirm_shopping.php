<?php

$connection = mysqli_connect("localhost","root","","sohozshopping")
or die("Error " . mysqli_error($connection));

$username = $_GET['username'];

//$sql_product_price = "select * from customer_shopping where confirm = 0";
$sql = "select * from customer_shopping where confirm = 0 and username = '$username' ";
$result = mysqli_query($connection, $sql)
or die("Error in Selecting " . mysqli_error($connection));

$emparray = array();

while($row =mysqli_fetch_assoc($result))
{
    $emparray[] = $row;
}

echo json_encode($emparray);

mysqli_close($connection);

?>