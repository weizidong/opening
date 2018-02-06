<style lang="less">
  @import "../../base.less";

  .positionItem {
    margin-right: 7.5px;
    margin-left: 7.5px;
    margin-bottom: 15px;
    border-radius: 8px;
    border: none;
    .el-card__body {
      padding: 0;
    }
    .card_top {
      display: flex;
      padding: 0 0 0 30px;
      i {
        float: left;
        width: 56px;
        height: 56px;
        background-color: #eee;
        margin: 15px 18px 0 0;
        border-radius: 50%;
      }
    }
    .card_top_right {
      float: right;
      .text-overflow{
        word-break: break-all;
        display: -webkit-box; /** 对象作为伸缩盒子模型显示 **/
        -webkit-box-orient: vertical; /** 设置或检索伸缩盒对象的子元素的排列方式 **/
        -webkit-line-clamp: 1; /** 显示的行数 **/
      }
    }
    .card_top_right p:nth-child(1) {
      font-size: @fontSize20;
      margin-top: 10px;
    }
    .card_top_right p:nth-child(2) {
      color: #97a8bd;
      font-size: @fontSize16;
    }
    .card_middle {
      padding-left: 35px;
      margin: 30px 0 10px;
      p {
        display: flex;
        align-items: center;
        i {
          width: 15px;
          height: 15px;
          display: inline-block;
          background-color: #eee;
          margin-right: 5px;
        }
        .card_middle_span1 {
          color: #97a8bd;
          font-size: @fontSize16;
          margin-right: 12px;
        }
      }
      .card_middle_p1 .card_middle_span2 {
        font-size: @fontSize20;
        color: #2ca2fa;
      }
      .card_middle_p2 .card_middle_span2 {
        font-size: @fontSize20;
      }
    }
    .card_bottom {
      padding: 10px 0;
      background-color: @bg-area;
      display: flex;
      justify-content: space-around;
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

</style>

<template>
  <el-card class="positionItem">
    <div class="card_top">
      <i><img src="" alt=""></i>
      <div class="card_top_right">
        <p class="text-overflow">{{item.qjcName}}</p>
        <p class="text-overflow">{{item.qcShortName}}</p>
      </div>
    </div>
    <div class="card_middle">
      <p class="card_middle_p1"><span class="card_middle_span1"><i></i>职位薪资</span><span class="card_middle_span2">{{item.qjMinMoney}}K-{{item.qjMaxMoney}}K</span><span class="card_middle_span2"></span></p>
      <p class="card_middle_p2"><span class="card_middle_span1"><i></i>公司类型</span><span class="card_middle_span2">国有企业</span></p>
    </div>
    <div class="card_bottom">
      <p>
        <span>{{item.qccName}}</span>
        <span>工作地</span>
      </p>
      <p>
        <span>{{item.qjEdu}}</span>
        <span>学历</span>
      </p>
      <p>
        <span>{{item.qjExperience}}</span>
        <span>工作经验</span>
      </p>
    </div>
  </el-card>
</template>

<script>
  import {defValFilter02} from '../../constant/filter'

  export default {
    name: 'positionItem',
    method: {
      defValFilter02,
    },
    props: {
      'item': Object,
    },
    created() {
    },
  }
</script>
