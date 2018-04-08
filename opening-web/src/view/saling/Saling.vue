<style lang="less" scoped>
  @import "./saling.less";
</style>
<template>
  <div class="saling">
    <div class="text-center">
      <el-tag type="warning" class="title">销控中心</el-tag>
    </div>
    <div v-for="(build,idx) in list" class="build">
      <el-card class="box-card">
        <div slot="header" class="build_no text-center">
          <el-tag type="success">{{idx}}号楼</el-tag>
        </div>
        <div v-for="(floor,index) in build" class="floor item">
          <el-tag>{{index}}楼</el-tag>
          <div v-for="(house,i) in floor" class="house">
            <el-tag :type="house.userId?'danger':'info'">{{house.roomNo}}</el-tag>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {allHouseApi} from '../../api/houseApi'
  import webSocket from '../../actions/webSocket'

  export default {
    name: "saling",
    data() {
      return {
        list: {},
      }
    },
    created() {
      allHouseApi().then(list => {
        this.list = list
      })
      webSocket.init(`ws://${location.host}/websocket/0`, {debug: false})
      // webSocket.init(`ws://localhost:8090/websocket/0`, {debug: true})
      webSocket.onMessage = ({command, data}) => {
        if (command === '@notice') {
          const {user, house} = data
          const h = this.list[house.buildingNo][house.floorNo].find(u => u.roomNo === house.roomNo)
          if (h) {
            h.userId = user.id
          }
          this.$notify({
            title: '认筹通知',
            message: `恭喜${user.name}购得${house.roomNo}房源!`,
            position: 'bottom-right',
            type: 'success'
          });
        }
      }
    },
    destroyed() {
      webSocket.close()
    }
  }
</script>
