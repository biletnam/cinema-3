$(document).ready(function(){

	$.getJSON('/load', function(seats){
		$("#added-books").append("<tr><td colspan='2'></td><td colspan=11><div class='screen'>screen</div></td><td colspan='2'></td></tr>")
			.append("<tr><td colspan='15'>&nbsp</td></tr>")
			.append("<tr id = 'tr1'></tr>");
		var tr = $("#tr1");
		var raw = 1;

		$.each(seats, function(i, item){
			if (item.raw != raw){
				$("#added-books").append("<tr id = 'tr" + (++raw) + "'></tr>");
				tr = $("#tr" + raw);
			}

			if (item.status==0) tr.append("<td><input type=checkbox name=id value=" + item.id + "></td>");
			else tr.append("<td><input type=checkbox disabled name=id value="+item.id+"></td>");
		})
	});


	$("#buy").click(function(){
		$("#sum").remove();

		var data=$("#allBookForm").serializeArray();
		$.ajax({
			url: "select_info",
			type: "POST",
			dataType: 'JSON',
			data: {seatData: JSON.stringify(data)},
			success: function (seats) {
				console.log(data);
				$("#buy").remove();

				var tab = $("#table_block").append("<div id ='block' style='width:500px'>"+
					"<h2 style='color:#ccc'><small>Your seats</small></h2>");

				$.each(seats, function (i, seat) {
					$("#block").append("<p><span>place:"+seat.place+", raw:"+seat.raw+", price:"+seat.price+"</span></p>");
				});
			},
			error:function(data,status,er) {
				alert("error: "+data+" status: "+status+" er:"+er);
			}
		});

		$.ajax({
			url: "select",
			type: "POST",
			dataType: 'JSON',
			data: {seatData: JSON.stringify(data)},
			success: function (seats) {
				console.log(data);
				$("#added-books").empty();
				$("#added-books").append("<tr><td colspan='2'></td><td colspan=11><div class='screen'>screen</div></td><td colspan='2'></td></tr>")
					.append("<tr><td colspan='15'>&nbsp</td></tr>")
					.append("<tr id = 'tr1'></tr>");
				var tr = $("#tr1");
				var raw = 1;

				$.each(seats, function(i, item){
						if (item.raw != raw){
							$("#added-books").append("<tr id = 'tr" + (++raw) + "'></tr>");
							tr = $("#tr" + raw);
						}

						if (item.status==0) tr.append("<td><input type=checkbox name=id value=" + item.id + "></td>");
						else tr.append("<td><input type=checkbox disabled name=id value="+item.id+"></td>");
				})
			},
			error:function(data,status,er) {
				alert("error: "+data+" status: "+status+" er:"+er);
			}
		});
	});

	$("#total_price").click(function(){
		$.getJSON('/reserve', function(total_sum){
			$("#sum").remove();
			$("#block").remove();
			$("#total_price").append("<div><button type='button' id='sum' class='btn btn-default'>"+total_sum+" grn</a></button></div>")
		});
	});
});
