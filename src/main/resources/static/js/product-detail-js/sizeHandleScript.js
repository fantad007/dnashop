$( document ).ready(function() {
    var productId = $( "#productId" ).text();
    var sizeId;
    $(".size").click(function() {
        $("#add-to-cart").removeAttr("href");
        $(".size").removeClass("chooseSize");
        $(this).addClass("chooseSize");
        sizeId = $(this).attr('value');
        $(this).on("blur",function(){
            $("#form-size").submit();
        });
        $.ajax({
            dataType: 'json',
            url: '/api/san-pham/chi-tiet-san-pham/cap-nhat-size/' + sizeId,
            type: 'get',
            success: function(data) {
                var result = "";
                data.forEach(item => {
                    result += `
                        <span class="color" value="${item.colorId}">${item.colorName}</span>
                    `;
                })
                $("span").remove(".color");
                $(".color-li").append(result);
            },
        });
        $(document).on('click', 'span.color', function() {
            $(".color").removeClass("chooseColor");
            $(this).addClass("chooseColor");
            var colorId = $(this).attr('value');
            $("#add-to-cart").attr("href", "/gio-hang/them/" + productId + "/" + sizeId + "/" + colorId);
        });
    });
});
