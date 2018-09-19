<?php

$host = "localhost";
$user = "root";
$password = "";
$database = "sohozshopping";


try {
    $db = new PDO("mysql:host = $host; dbname=$database", $user, $password);
    // set the PDO error mode to exception
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (Exception $exc) {
    echo $exc->getMessage();
}
