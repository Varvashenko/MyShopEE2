<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.MyShopEE.FruitsLoader" %>
<%
    FruitsLoader fruitsLoader = new FruitsLoader();
    fruitsLoader.loadFruits();
%>
    <!DOCTYPE html>
<html>

<head>
    <title>My Best Shop</title>
    <meta charset="utf-8" />
</head>

<body>
<h1>My best shop of Fruits.</h1>
<%--<form action="shop-servlet" method="POST">--%>

<hr />
<div style="display:flex;">
    <ul class="ul-left" id="id-ul-left">
        <%
            String s = "<li id='liLeftObject-%d'>%s</li>";
            for(int i = 0; i < fruitsLoader.getCountOfFruits(); i ++) {
                out.println( String.format(s, i+1, fruitsLoader.getFruits(i)) );
            }
        %>
    </ul>
    <ul class="ul-right" id="id-ul-right">
    </ul>
</div>

<hr />

<form action="" method="GET" class="class-input">
    New fruit :
    <input type='text' id='input-text' name='fruitname' placeholder='<new fruit>' autocomplete="off" required class='class-input' />
    <input type='submit' id='input-button' class='class-input' value='Send new fruit' />
</form>

<hr />
<p>Fruit: ${fruit}</p>
<p>Count <%= fruitsLoader.getCountOfFruits() %></p>

</body>

</html>

<style>
    h1 {
        font-size: 32px;
        font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
        text-align: center;
        color: darkgreen;
        padding: 10px;
    }

    li {
        font-size: 24px;
        font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
        /* text-align: center; */
        color: darkmagenta;
        padding: 5px;
    }

    .class-input {
        font-size: 24px;
        font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
        /* text-align: center; */
        color: darkmagenta;
    }

    .ul-left li:hover {
        background-color: lightgreen;
    }

    .ul-left li::first-letter {
        color: red;
    }

    .ul-left {
        /* display: inline-block; */
        width: 40%;
        float: left;
    }

    .ul-right li:hover {
        background-color: lightsalmon;
    }

    .ul-right li::first-letter {
        color: red;
    }

    .ul-right {
        /* display: inline-block; */
        width: 40%;
        /* float: right; */
    }
</style>

<script>
    var arrayFruits = {
        fruit: [],
        count: [],
        getCount: function () {
            return this.fruit.length;
        },
        setFruit: function (_fruit) {
            var index = -1;
            for (var i = 0; i < this.getCount(); i++) {
                if (_fruit === this.fruit[i]) index = i;
            }
            if (index < 0) {
                this.fruit.push(_fruit);
                this.count.push(1);
            } else {
                this.count[index] = this.count[index] + 1;
            }
            return index;
        },
        findFruit: function (_fruit) {
            var index = -1;
            for (var i = 0; i < this.getCount(); i++) {
                if (_fruit === this.fruit[i]) index = i;
            }
            this.count[index] = this.count[index] - 1;
            if (this.count[index] === 0) {
                this.fruit.splice(index, 1);
                this.count.splice(index, 1);
            }
            return index;
        }
    }

    function reFuckRight() {
        var w = '';
        for (var i = 0; i < arrayFruits.getCount(); i++) {
            w += '<li id="liRightObject-' + (i + 1) +
                '" data-name="' + arrayFruits.fruit[i] + '">' +
                arrayFruits.fruit[i] + ' ( ' + arrayFruits.count[i] + ' sht )</li>\n';
        }
        document.getElementById('id-ul-right').innerHTML = w;
        // window.alert(w);
        var v;
        for (var i = 0; i < arrayFruits.getCount(); i++) {
            v = document.getElementById('liRightObject-' + (i + 1));
            v.addEventListener('click', liRightOnClick);
        }
    }

    function liRightOnClick(obj) {
        var s = obj.currentTarget.getAttribute('data-name');
        arrayFruits.findFruit(s);
        reFuckRight();
    }

    function liLeftOnClick(obj) {
        arrayFruits.setFruit(obj.currentTarget.innerHTML);
        reFuckRight();
    }

    function imputNewFruitOnClick(obj) {
        var textNewFruit = document.getElementById('input-text');
        window.alert(textNewFruit.value);
        textNewFruit.value = '';
        window.alert( '${fruit}' );
    }


    var ul = document.getElementById('id-ul-left');
    var countLI = ul.getElementsByTagName("LI").length;
    var v;
    for (var i = 1; i <= countLI; i++) {
        v = document.getElementById('liLeftObject-' + i);
        v.addEventListener('click', liLeftOnClick);
    }

    // var imputNewFruit = document.getElementById('input-button');
    // imputNewFruit.addEventListener('click', imputNewFruitOnClick);
</script>