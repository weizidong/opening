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
        // webSocket.init(`ws://${location.host}/websocket/${user.id}`, {debug: true})
        webSocket.init(`ws://localhost:8090/websocket/${user.id}`, {debug: true})
        webSocket.onMessage = ({command, data}) => {
          if (command === '@notice') {
            console.log(data)
          }
        }
        webSocket.send('asdasdasdasdasdasdsasd')
      })
    },
    destroyed() {
      webSocket.close()
    }
  }
</script>
