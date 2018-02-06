<style lang="less">
  @import "../../base.less";

  .companyItem {
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
      padding: 0 0 0 18px;
      i {
        float: left;
        width: 56px;
        height: 56px;
        background-color: #eee;
        margin: 15px 30px 0 0;
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
      padding-left: 18px;
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
  <el-card class="companyItem">
    <div class="card_top">
      <i><img src="" alt=""></i>
      <div class="card_top_right">
        <p class="text-overflow">{{defValFilter02(item.qcFullName)}}</p>
        <p class="text-overflow">{{defValFilter02(item.qcLbs)}}</p>
      </div>
    </div>
    <div class="card_middle">
      <p class="card_middle_p1"><i></i><span class="card_middle_span1">公司认证</span><span
        class="card_middle_span2">defValFilter02</span></p>
      <p class="card_middle_p2"><i></i><span class="card_middle_span1">公司类型</span><span
        class="card_middle_span2">国有企业</span></p>
    </div>
    <div class="card_bottom">
      <p>
        <span>{{defValFilter02(item.qcProvince)}}</span>
        <span>所在地</span>
      </p>
      <p>
        <span>{{defValFilter02(item.qiName)}}</span>
        <span>行业类别</span>
      </p>
      <p>
        <span>{{defValFilter02(item.qcFinancing)}}</span>
        <span>融资情况</span>
      </p>
      <p>
        <span>{{defValFilter02(item.qcPeople)}}</span>
        <span>公司规模</span>
      </p>
    </div>
  </el-card>
</template>

<script>
  import {rzValFilter, defValFilter02} from '../../constant/filter'

  export default {
    name: 'companyItem',
    method: {
      rzValFilter,
      defValFilter02,
    },
    props: {
      'item': Object,
    },
    created() {
    },
  }
</script>
