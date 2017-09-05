<template>
    <div class="container">
        <div class="title">title区域</div>
        <div class="welcome">welcome区域</div>
        <div class="talk">会话区域</div>
        <div class="guide">guide区域</div>
        <div class="input">input区域</div>
    </div>
</template>

<script>
    
</script>

<style>
    .container{
        border:1px solid #333;
        width:100%;
        height:450px;
        display:flex;
        flex-direction:column;
    }
    .title{
        height:5%;
        border-bottom: 1px solid #333;
    }
    .welcome{
        height:25%;
        border-bottom: 1px solid #333;
    }
    .talk{
        flex:1;
        border-bottom: 1px solid #333;
    }
    .guide{
        height:10%;
        border-bottom: 1px solid #333;
    }
    .input{
        height:5%;
    }
</style>