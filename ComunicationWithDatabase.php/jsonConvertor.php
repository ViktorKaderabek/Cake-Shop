<?php
require "DataBase.php";
$db = new DataBase();
    if ($db->dbConnect()) {
        if ($db->convertDataToJson("users")) {
            echo "Login Success";
        } else echo "Username or Password wrong";
    } else echo "Error: Database connection";
?><?php
