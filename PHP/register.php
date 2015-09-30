<?php
$first = $_GET['first'];
$last = $_GET['last']; 
$email = $_GET['email'];
$superusername = $_GET['superusername'];
$superpassword = $_GET['superpassword'];
$birthday = $_GET['birthday'];
$male = $_GET['male'];
$female = $_GET['female'];
$other = $_GET['other'];
$weight = $_GET['weight'];
$height = $_GET['height'];


if($first == '' || $last == '' || $email == '' || $superusername == '' || $superpassword == '' || $birthday == '' || $male == '' || $female == '' || $other == '' || $weight == '' || $height == ''){
	echo 'please fill all values';
}else{
	require_once('dbConnect.php');
	$sql = "SELECT * FROM user WHERE superusername='$superusername' OR email='$email'";

	$check = mysqli_fetch_array(mysqli_query($con,$sql));

	if(isset($check)){
		echo 'username or email already exist';
	}else{
		$sql = "INSERT INTO user (first, last,email,superusername,superpassword,birthday, male, female, other, weight, height) VALUES('$first','$last','$email','$superusername','$superpassword','$birthday','$male','$female','$other','$weight','$height')";
		if(mysqli_query($con,$sql)){
			echo 'successfully registered';
		}else{
			echo 'oops! Please try again!';
		}
	}
	mysqli_close($con);
}
?>
