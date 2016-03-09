<?php
	// Merchant key here as provided by Payu
	$MERCHANT_KEY = "5361346";

	// Merchant Salt as provided by Payu
	$SALT = "GQs7yium";

	// End point - change to https://secure.payu.in for LIVE mode
	$PAYU_BASE_URL = "https://test.payu.in";

	$action = '';

	$posted = array();
	if(!empty($_POST)) {
		//print_r($_POST);
		foreach($_POST as $key => $value) {    
			$posted[$key] = $value; 
		}
	}

	$formError = 0;

	if(empty($posted['txnid'])) {
		// Generate random transaction id
		$txnid = substr(hash('sha256', mt_rand() . microtime()), 0, 20);
	} else {
		$txnid = $posted['txnid'];
	}

	$hash = '';

	// Hash Sequence
	$hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";

	if(empty($posted['hash']) && sizeof($posted) > 0) {
		if(
			empty($posted['key'])
			|| empty($posted['txnid'])
			|| empty($posted['amount'])
			|| empty($posted['firstname'])
			|| empty($posted['email'])
			|| empty($posted['phone'])
			|| empty($posted['productinfo'])
			|| empty($posted['surl'])
			|| empty($posted['furl'])
			|| empty($posted['service_provider']) ) {
	    			$formError = 1;
	  	} else {
			//$posted['productinfo'] = json_encode(json_decode('[{"name":"tutionfee","description":"","value":"500","isRequired":"false"},{"name":"developmentfee","description":"monthly tution fee","value":"1500","isRequired":"false"}]'));
			$hashVarsSeq = explode('|', $hashSequence);
	   		$hash_string = '';	
			foreach($hashVarsSeq as $hash_var) {
				$hash_string .= isset($posted[$hash_var]) ? $posted[$hash_var] : '';
				$hash_string .= '|';
			}

			$hash_string .= $SALT;

			$hash = strtolower(hash('sha512', $hash_string));
			$action = $PAYU_BASE_URL . '/_payment';
		}
	} elseif(!empty($posted['hash'])) {
		$hash = $posted['hash'];
		$action = $PAYU_BASE_URL . '/_payment';
	}
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Checkout</title>
		<link rel="stylesheet" type="text/css" href="css.css">
		<script type="text/javascript" href="js.js"></script>
	</head>
	<body>
		<header>
			<section id="topbar">
				<a href="" class="website-logo-link">
					<img src="logo.svg" width="237px" height="100px" title="Website Name">
				</a>
				<img src="img2.png" class="website-imgs" width="230px" height="100px">
				<img src="img1.png" class="website-imgs" width="280px" height="100px">
				<img src="img3.jpg" class="website-imgs" width="280px" height="100px">
				<div class="clear"></div>
			</section>
			<nav>
				<ul>
					<li>Home</li>
					<li>Signup</li>
					<li>Shopping Cart</li>
					<li>Contact Us</li>
				</ul>
				<div class="clear"></div>
			</nav>
		</header>
		<form action="<?php echo $action; ?>" method="post" name="payuForm">
			<input type="hidden" name="key" value="<?php echo $MERCHANT_KEY ?>" />
			<input type="hidden" name="hash" value="<?php echo $hash ?>"/>
			<input type="hidden" name="txnid" value="<?php echo $txnid ?>" />
			<div <?php if(!empty($hash) ) { ?>class="hidden"<?php } ?>>
						<!--	<img src="img4.jpg" class="web-imgs" width="280px" height="100px">-->

				<h2>PayU Form</h2>
				<br/>
				<?php if($formError) { ?>
					<span style="color:red">Please fill all mandatory fields.</span>
					<br/>
					<br/>
				<?php } ?>
				<table>
					<tr>
						<td><b>Mandatory Parameters</b></td>
					</tr>
					<tr>
						<td>Amount: </td>
						<td><input name="amount" value="<?php echo (empty($posted['amount'])) ? '' : $posted['amount'] ?>" /></td>
						<td>First Name: </td>
						<td><input name="firstname" id="firstname" value="<?php echo (empty($posted['firstname'])) ? '' : $posted['firstname']; ?>" /></td>
					</tr>
					<tr>
					<td>Email: </td>
						<td><input name="email" id="email" value="<?php echo (empty($posted['email'])) ? '' : $posted['email']; ?>" /></td>
						<td>Phone: </td>
						<td><input name="phone" value="<?php echo (empty($posted['phone'])) ? '' : $posted['phone']; ?>" /></td>
					</tr>
					<tr>
						<td>Product Info: </td>
						<td colspan="3"><textarea name="productinfo"><?php echo (empty($posted['productinfo'])) ? '' : $posted['productinfo'] ?></textarea></td>
					</tr>
					<tr>
						<td>Success URI: </td>
						<td colspan="3"><input name="surl" value="<?php echo (empty($posted['surl'])) ? '' : $posted['surl'] ?>" size="64" /></td>
					</tr>
					<tr>
						<td>Failure URI: </td>
						<td colspan="3"><input name="furl" value="<?php echo (empty($posted['furl'])) ? '' : $posted['furl'] ?>" size="64" /></td>
					</tr>
					<tr>
						<td colspan="3"><input type="hidden" name="service_provider" value="payu_paisa" size="64" /></td>
					</tr>
										<tr>
					  <?php if(!$hash) { ?>
					    <td colspan="4"><input type="submit" value="Submit" /></td>
					  <?php } ?>
					</tr>
				</table>
			</div>
			<div <?php if(empty($hash) ) { ?>class="hidden"<?php } ?>>
				<section id="cart-details">
					<b>Welcome <?php echo $posted['email']; ?>. <span class="color-yellow">Your Shopping Cart</span> - Total Items : 1, Total Amount : <?php echo $posted['amount']; ?></b>
				</section>
				<section id="cart-proceed">
					Pay with Payu<br>
					<button type="submit">Proceed >></button>
				</section>
			</div>
		</form>
	</body>
</html>
