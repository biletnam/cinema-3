$(document).ready(function(){

	$.getJSON("/load", function(seats){
		var tr = $("#tr1");
		var rowNum = $(".rowsNumbers");
		var raw = 0;

		$.each(seats, function(i, item){
			if (item.raw != raw){
				$("#added-books").append("<tr id = 'tr" + (++raw) + "'></tr>");
				rowNum.append("<tr><td>Row "+raw+"</td></tr>");
				tr = $("#tr" + raw);
				seat = 1;
			}

			if (item.status==0) tr.append("<td><input type=checkbox name=id value=" + item.id + " id = " + item.id + ">" +
				"<label class = 'hover"+seat+" check' for = "+item.id+"></label></td>");
			else tr.append("<td><input type=checkbox disabled name=id value="+item.id+">" +
				"<label class = disabled for = "+item.id+"></label></td>");
		})
	});


	$("#buy").click(function(){
		$("#sum").remove();

		var data=$("#allBookForm").serializeArray();
		$.ajax({
			url: "select",
			type: "POST",
			dataType: 'JSON',
			data: {seatData: JSON.stringify(data)},
			success: function (seats) {
				$("#added-books").empty();
				var tr = $("#tr1");
				var raw = 0;
				var seat = 1;

				$.each(seats, function(i, item){
					if (item.raw != raw){
						$("#added-books").append("<tr id = 'tr" + (++raw) + "'></tr>");
						tr = $("#tr" + raw);
					}

					if (item.status==0) tr.append("<td><input type=checkbox name=id value=" + item.id + " id = " + item.id + ">" +
						"<label class = 'hover"+seat+" check' for = "+item.id+"></label></td>");
					else tr.append("<td><input type=checkbox disabled name=id value="+item.id+">" +
						"<label class = disabled for = "+item.id+"></label></td>");
				})
			},
			error:function(data,status,er) {
				alert("error: "+data+" status: "+status+" er:"+er);
			}
		});

		$.ajax({
			url: "select_info",
			type: "POST",
			dataType: 'JSON',
			data: {seatData: JSON.stringify(data)},
			success: function (seats) {
				$("#buy").remove();

				$(".hallInfo").append("<div id ='block'><span id='span'>Your seats</sp></div>");
				$.each(seats, function (i, seat) {
					$("#block").append("<p><span>place:"+seat.place+", raw:"+seat.raw+", price:"+seat.price+"</span></p>");
				});
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
			$("#total_price").append("<div><span id='sum' class=''>"+total_sum+" grn</span></div>")
		});
	});
});


