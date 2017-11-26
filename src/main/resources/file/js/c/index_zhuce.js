/*
 * 注册块的表单验证
 */
$('#submit0').click(
		function() {
			if ($("#subusername").val().trim() == "") {
				$("#sizing-addon1").html("<span>请填写用户名</span>")
				$("#formdiv1").prop("class", "modal-body has-error");
			}
			if ($("#subpassword").val().trim() == "") {
				$("#sizing-addon2").html("<span>请填写密码</span>");
				$("#formdiv2").prop("class", "modal-body has-error");
			}
			if ($("#subpassword1").val().trim() == "") {
				$("#sizing-addon3").html("<span>请确认您输入的密码</span>")
				$("#formdiv3").prop("class", "modal-body has-error");
			}
			if ($("#subemail").val().trim() == "") {
				$("#sizing-addon4").html("<span>请填写邮箱</span>")
				$("#formdiv4").prop("class", "modal-body has-error");
			}
			if ($("#subusername").val().trim() != ""
					|| $("#subpassword").val().trim() != ""
					|| $("#subpassword1").val().trim() != ""
					|| $("#subemail").val().trim() != "") {
				$("#submit0").html("注册中...")
				$("#submit0").attr({
					"disabled" : "disabled"
				});
				$
						.ajax({
							type : "POST",
							url : "/ajax/",
							async : true,
							dataType : "json",
							data : {
								"from" : "index_zhuce",
								"index_zhuce_username" : $("#subusername")
										.val().trim(),
								"index_zhuce_password" : $("#subpassword")
										.val().trim(),
								"index_zhuce_password1" : $("#subpassword1")
										.val().trim(),
								"index_zhuce_email" : $("#subemail").val()
										.trim()
							},

							success : function(data, textStatus) {
								var resultData = data;
								$.each(resultData, function(index, item) {
									if ("false" == item.username) {
										$('#sizing-addon1').html(
												"<span>用户名已被占用</span>");
										$("#formdiv1").prop("class",
												"modal-body has-error");
										$("#submit0").html("注册")
										$("#submit0").removeAttr("disabled");
									}
									if ("false" == item.password) {
										$('#sizing-addon2').html(
												"<span>密码不安全</span>");
										$("#formdiv2").prop("class",
												"modal-body has-error");
										$("#submit0").html("注册")
										$("#submit0").removeAttr("disabled");
									}
									if ("false" == item.password1) {
										$('#sizing-addon3').html(
												"<span>密码不正确</span>");
										$("#formdiv3").prop("class",
												"modal-body has-error");
										$("#submit0").html("注册")
										$("#submit0").removeAttr("disabled");
									}
									if ("false" == item.email) {
										$('#sizing-addon4').html(
												"<span>邮箱不正确</span>");
										$("#formdiv4").prop("class",
												"modal-body has-error");
										$("#submit0").html("注册")
										$("#submit0").removeAttr("disabled");
									}

									if ("true" == item.username
											&& "true" == item.password
											&& "true" == item.password1
											&& "true" == item.email) {
										$('#register_zhuce').submit()
									}
								});
							}
						});
			}
		});