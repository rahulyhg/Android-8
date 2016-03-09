<?php 
	/******************************************************************/
	/* STANDARD INCLUDES                                              */
	/******************************************************************/
	include("tConf.php");
	include("lib/db.php");
	include("lib/utils.php");
	include("lib/users/model.php");
	include("lib/users/db.php");
	include("modules/utils/utils.php");
	include("modules/utils/settings.php");
	include("modules/utils/db.php");
	
	LOG_MSG('INFO',"=========================================== START ===========================================");

?>

<body > 

	<!-- BACKGROUND BAR -->
	<div class="ui-widget-header background-bar" style='border:none'>&nbsp;</div>

	<!-- CONTAINER -->
		<div class="container">
			<!-- PAGE TITLE -->
			<div class="ui-widget-header page-title" style='border:none'>
				<a href="<?php echo BASEURL; ?>"><div style="background: url('static/images/e42-logo-Text.png') no-repeat 0% -20%;background-size:200px 55px;"class="span-13">
					<div style="margin-left:220px;color:white;font-size:33px;"><?php echo TRAVEL_NAME; ?></div>
				</div></a>
				<div style="float:right">
					<?php if ( !is_loggedin() ) { ?>
						<form method="POST" action="index.php">
							<input type="hidden" name="do" value="login"/>
							<p class="span-4">Username
								<input type="text" name="lemail_id" id="lemail_id"  value="" style='padding:4px 1px;'/>
							</p>
							<p class="span-4">Password
								<input type="password" name="lpassword" id="lpassword"  value="" style='padding:4px 1px;'/>
							</p>
							<div class="span-2"><input type="submit" value="Login" style='margin-top:23px'/></div>
						</form>
					<?php } else { 
						if ( is_admin()) { ?> <span style="float:left;" class="ui-icon ui-icon-star"></span> <?php } 
						if ( is_supervisor() ) { ?> <span style="float:left;" class="ui-icon ui-icon-person"></span> <?php } ?>

						<?php if ( is_admin() ) { ?><a style="font-weight:normal;" 
						href="index.php?mod=admin&ent=user&go=modify&user_id=<?php echo $_SESSION['user_id'];?>"><?php } ?>
								<?php echo ucwords(strtolower(get_arg($_SESSION,'name')));?>
						<?php if ( is_admin() ) { ?></a><?php } ?>|
						<a style="font-weight:normal;" href="javascript:void(0)" onclick='LogoutForm.submit();'>Logout</a>
						<form method="POST" action="index.php" name="LogoutForm">
							<input type="hidden" name="do" value="logout"/>
						</form>
					<?php }?>
				</div>
			</div>

			<?php if( is_loggedin() ) {?>
				<!-- TOP MENU -->
				<div class="span-24" style="align:center;">
					<?php include("static/html/top_menu.html"); ?>
				</div>
			<?php } ?>

			<!-- CENTER CONTENT -->
			<div class="span-24 center-content">
					<?php 
						show_msgs();
						if( is_loggedin()  && is_admin() || is_viewer() )  {
							if (file_exists("modules/$MOD/$ENTITY/controller.php")) {
								include("modules/".$MOD."/".$ENTITY."/controller.php"); 
							}
						}
							else
							if( is_loggedin()  && is_supervisor()) {
								include("modules/admin/vehicle/controller.php"); 
							}
							else
							if( is_loggedin()  && is_superuser()) {
								include("modules/admin/travel/controller.php"); 
							}
							else {
							add_msg('SUCCESS',"Welcome to $DOMAIN. Please login to start using your account");
							show_msgs();
						}
						
					?>
			</div>
			<!-- END CENTER CONTENT -->

			<!-- FOOTER -->
			<?php include("static/html/footer.html"); ?>

		</div> 	<!-- END CONTAINER -->

</body>
</html>

<?php 
db_close();
LOG_MSG('INFO',"=========================================== END ==========================================="); 
?>
