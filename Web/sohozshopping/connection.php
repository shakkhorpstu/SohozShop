<?php
 define('HOST','localhost');
 define('USER','root');
 define('PASS','');
 define('DB','my_shop');

 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 
 ?>