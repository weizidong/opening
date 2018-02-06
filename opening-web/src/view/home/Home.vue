<style lang="less" scoped>
  @import "./home.less";
</style>

<template>
  <div class="home">
    <el-card v-for="(item,i) in statistic" :key="i">
      <div>{{item.count}}</div>
      <div>{{item.title}}</div>
    </el-card>
  </div>
</template>

<script>
  import {getStatisticApi} from '../../api/systemApi'

  export default {
    name: 'home',
    data() {
      return {
        statistic: [{filed: 'topicCount', title: '新增提问', count: 0},
          {filed: 'backSxtopicCount', title: '待回复问题', count: 0},
          {filed: 'userCount', title: '用户总量', count: 0},
          {filed: 'userRollCount', title: '今日注册', count: 0},
          {filed: 'orderCount', title: '今日订单', count: 0},
          {filed: 'articleCount', title: '文章总数', count: 0}],
      }
    },
    created() {
      getStatisticApi().then(data => {
        this.statistic.forEach(item => {
          item.count = data[item.filed]
        })
      })
    },
  }
</script>
