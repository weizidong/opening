<style lang="less" scoped>
  @import "./saling.less";
</style>
<template>
  <div>
    <div v-for="(build,idx) in list">
      {{idx}}号楼
      <div v-for="(floor,index) in build">
        {{index}}层
        <div v-for="(house,i) in floor">
          {{house}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {allHouseApi} from '../../api/houseApi'

  export default {
    name: "saling",
    data() {
      return {
        list: [],
      }
    },
    created() {
      allHouseApi().then(list => {
        this.list = list
      })
    }
  }
</script>
