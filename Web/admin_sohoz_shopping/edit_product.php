<?php
if (!isset($_REQUEST['id'])) {
    header("location: product_list.php");
} else {
    $id = $_REQUEST['id'];
}
?>
<?php
include_once './database_file.php';
$statement = $db->prepare("SELECT * FROM product_list WHERE id=?");
$statement->execute(array($id));
$result = $statement->fetchAll(PDO::FETCH_ASSOC);
foreach ($result as $row) {
    $name = $row['product_name'];
    $description = $row['product_description'];
    $image = $id.'.jpg';
    $price = $row['product_price'];
       
}
?>

<?php

if(isset($_POST['form_update'])){
    try {

        if (empty($_POST['product_name'])) {
            throw new Exception("Product Name can not be empty.");
        }

        if (empty($_POST['product_description'])) {
            throw new Exception("Product Description can not be empty.");
        }
        if (empty($_POST['product_price'])) {
            throw new Exception("Product Price can not be empty.");
        }

        $statement = $db->prepare("UPDATE product_list SET product_name=?, product_description=?, product_price=? WHERE id=?");
        $statement->execute(array($_POST['product_name'], $_POST['product_description'], $_POST['product_price'], $id));

        $success_message = "Product updated successfully.";
    } catch (Exception $e) {
        $error_message = $e->getMessage();
    }
}

?>

<html>
    <title>edit product</title>
    <head>
        <link rel="stylesheet" href="admin_design.css">
    </head>
    <body>
        <ul style="margin-left: 400px;" id="menu">
			<li><a style="text-decoration: none" href="index.php">Add Product</a></li>
            <li><a style="text-decoration: none" href="product_list.php">Products List</a></li>
            <li><a style="text-decoration: none" href="confirm_order_list.php">Completed Order List</a></li>
            <li><a style="text-decoration: none" href="unconfirm_order_list.php">Uncomplete Order List</a></li>
        </ul>
       <?php
        if (isset($error_message)) {
            echo $error_message;
        }
        if (isset($success_message)) {
            echo $success_message;
        }
?>
        <form action="" method="post" enctype="multipart/form-data">
            <table class="edit_form">
                <tr><td>Product Name</td></tr>
                <tr><td><input style="width: 400px;" type="text" name="product_name" value="<?php echo $name; ?>"> </td></tr>
                <tr><td>Product Description</td></tr>
                <tr><td><textarea cols="47" rows="10" type="text" name="product_description" value="<?php echo $description; ?>"> </textarea></td></tr>
                <tr><td>Product Price</td></tr>
                <tr><td><input style="width: 400px;" type="text" name="product_price" value="<?php echo $price; ?>"> </td></tr>
                <tr><td>Attached Image</td></tr>
                <tr><td><img src="uploads/<?php echo $image; ?>" alt="" width="200px"></td></tr>
                <tr><td><input style="background-color: #b9cec7;" type="submit" value="Save" name="form_update"></td></tr>
            </table>
        </form>
    </body>
</html>

