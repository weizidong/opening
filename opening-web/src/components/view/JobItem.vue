<style lang="less">
  @import "../../base.less";
  .jopItem {
    margin-right: 7.5px;
    margin-left: 7.5px;
    margin-bottom: 15px;
    border-radius: 8px;
    border: none;
    .card_top{
      text-align: center;
    }
    i{
      display: inline-block;
      width:94px;
      height:94px;
      background: #eee;
      border-radius: 50%;
    }
    .card_name{
      font-size: @fontSize20;
      margin: 15px 0;
    }
    .card_p1{
      font-size: @fontSize18;
      span{
        padding:2px 10px;
        color: @font-white;
        border-radius: 5px;
      }
      span:nth-child(1){
        background-color: @font-keyword1;
      }
      span:nth-child(2){
        background-color: @font-keyword2;
      }
      span:nth-child(3){
        background-color: @font-keyword3;
      }
    }
    .card_bottom {
      padding: 10px 0;
      display: flex;
      justify-content: space-around;
      margin-top: 15px;
      p span {
        display: block;
        text-align: center;
      }
      p span:nth-child(1) {
        font-size: @fontSize18;
        color: @font-word1;
      }
      p span:nth-child(2) {
        font-size: @fontSize16;
        color: @font-word2;
      }
    }
  }
  .jopItem:nth-child(4n) {
    margin-right:0!important;
  }

</style>

<template>
  <el-card class="jopItem">
    <div class="card_top">
      <i>
        <img class="card_top_img" src="" alt="">
        <img :src="item.quPhoto" alt="">
      </i>
      <p class="card_name">{{item.quName}}</p>
      <P class="card_p1">
        <span>JAVA</span>
        <span>JSP</span>
        <span>JSP</span>
      </P>
    </div>
    <div class="card_bottom">
      <p>
        <span>{{item.quEdu}}</span>
        <span>学历</span>
      </p>
      <p>
        <span>{{item.quJobAge}}</span>
        <span>工作经验</span>
      </p>
      <p>
        <span>{{item.qijMinPay-item.qijMaxPay}}</span>
        <span>期望薪资</span>
      </p>
    </div>
  </el-card>
</template>

<script>
  import {defValFilter02} from '../../constant/filter'
  export default {
    name: 'JobItem',
    props: {
      item: Object,
    },
    method: {
      defValFilter02,
    },
    created() {
    },
  }
</script>
