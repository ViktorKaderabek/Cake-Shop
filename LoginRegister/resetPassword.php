<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->resetPassword("users", $_POST['email'], $_POST['password'])) {
            echo "Your password has been updated";
        } else echo "Email doesn't exist";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>