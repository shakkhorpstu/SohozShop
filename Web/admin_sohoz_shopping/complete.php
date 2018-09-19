<?php
include_once './database_file.php';
?>

<?php
if (!isset($_REQUEST['id'])) {
    header("location: customer_shopping.php");
} else {
    $id = $_REQUEST['id'];
}
?>

<?php
    $statement = $db->prepare("UPDATE customer_shopping SET confirm = 1 WHERE id = ?");
    $statement->execute(array($id));
    header("location: unconfirm_order_list.php");
?>
