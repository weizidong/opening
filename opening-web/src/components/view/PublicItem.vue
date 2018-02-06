<style lang="less">
  @import "../../base.less";

  .publicItem {
    margin-right: 7.5px;
    margin-left: 7.5px;
    margin-bottom: 15px;
    border-radius: 8px;
    border: none;
    .el-card__body {
      padding: 0;
    }
    .publicItem_top {
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
    .publicItem_middle {
      .card_middle_top {
        .card_middle_p1 {
          width: 80px;
          height: 80px;
          background: #eee;
          margin: 15px auto 10px;
          border-radius: 50%;
          img {
            width: 80px;
            height: 80px;
            background: #eee;
          }
        }
        .card_middle_p2 {
          font-size: @fontSize20;
          text-align: center;
        }
        .card_middle_p3 {
          font-size: @fontSize16;
          color: @font-word2;
          text-align: center;
        }
        .card_middle_p4 {
          display: flex;
          justify-content: space-around;
          font-size: @fontSize18;
        }
      }
      .card_middle_bottom {
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
    }
    .publicItem_down {
      display: flex;
      justify-content: space-between;
      padding: 10px;
      border-top: 1px solid #eee;
      div {
        width: 50%;
        .publicItem_down_p1 {
          font-size: @fontSize18;
        }
        .publicItem_down_p2 {
          color: @font-word2;
          font-size: @fontSize18;
        }
      }
      p {
        text-align: center;
      }
      .publicItem_down_div1 {
        border-right: 1px solid #eee;
      }
    }
  }
</style>

<template>
  <el-card class="publicItem">
    <div class="publicItem_top">
      <p>
        <span>{{item.qjReward}}</span>
        <span>众招赏金</span>
      </p>
      <p>
        <span>{{item.qjRecruit}}</span>
        <span>招聘人数</span>
      </p>
      <p>
        <span>{{diffFilter(item.qjEndTime)}}</span>
        <span>剩余天数</span>
      </p>
    </div>
    <div class="publicItem_middle">
      <div class="card_middle_top">
        <p class="card_middle_p1">
          <img class="card_top_img" src="" alt="">
        </p>
        <p class="card_middle_p2">{{item.qjcName}}</p>
        <p class="card_middle_p3">{{item.qjName}}</p>
        <P class="card_middle_p4">
          <span><i></i>JAVA</span>
          <span><i></i>JSP</span>
          <span><i></i>JSP</span>
        </P>
      </div>
      <div class="card_middle_bottom">
        <p class="card_middle_p1"><span class="card_middle_span1"><i></i>职位薪资</span><span
          class="card_middle_span2">{{item.qjMinMoney}}K-{{item.qjMaxMoney}}K</span><span
          class="card_middle_span2"></span></p>
        <p class="card_middle_p2"><span class="card_middle_span1"><i></i>公司类型</span><span
          class="card_middle_span2">国有企业</span></p>
      </div>
    </div>
    <div class="publicItem_down">
      <div class="publicItem_down_div1">
        <p class="publicItem_down_p1">{{timeFilter(item.qjStartTime)}}<i>TO</i>{{timeFilter(item.qjEndTime)}}</p>
        <p class="publicItem_down_p2">悬赏时间</p>
      </div>
      <div class="publicItem_down_div2">
        <p class="publicItem_down_p1">{{item.qjRewardTime}}</p>
        <p class="publicItem_down_p2">赏金结算周期</p>
      </div>
    </div>
  </el-card>
</template>

<script>
  import {timeFilter, diffFilter} from '../../constant/filter'

  export default {
    name: 'publicItem',
    props: {
      item: Object,
    },
    methods: {
      timeFilter,
      diffFilter,
    },
    created() {
    },
  }
</script>
