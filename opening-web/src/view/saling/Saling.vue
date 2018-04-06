<style lang="less" scoped>
  @import "./saling.less";
</style>
<template>
  <div class="saling">
    <div v-for="(build,idx) in list" class="build">
      <div class="build_no">
        <el-tag type="success">{{idx}}号楼</el-tag>
      </div>

      <el-card class="box-card">
        <div v-for="(floor,index) in build" class="floor item">
          <el-tag>{{index}}楼</el-tag>
          <div v-for="(house,i) in floor" class="house">
            <el-tag type="info">{{house.roomNo}}</el-tag>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {allHouseApi} from '../../api/houseApi'
  import {mineApi} from '../../api/adminApi'
  import webSocket from '../../actions/webSocket'

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
      mineApi().then((user) => {
        webSocket.init(`ws://${location.host}/websocket/${user.id}`)
      })
    },
    destroyed() {
      webSocket.close()
    }
  }
</script>
