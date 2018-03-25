<style lang="less" scoped>
  /*.img_box {*/
  /*.img {*/
  /*max-width: 120px;*/
  /*max-height: 100px;*/
  /*}*/
  /*}*/
</style>
<template>
  <div class="content_data">
    <Breadcrumb/>
    <el-button type="primary" icon="el-icon-plus" @click="add">添加户型</el-button>
    <TablePage>
      <el-table :data="data.list" v-loading="data.loading" element-loading-text="拼命加载中...">
        <el-table-column prop="name" label="户型名称" :formatter="defValFilter01" min-width="140"/>
        <el-table-column prop="acreage" label="面积" :formatter="acreageFilter" min-width="180"/>
        <el-table-column prop="spec" label="户型" :formatter="defValFilter01" min-width="180"/>
        <!--<el-table-column prop="pics" label="户型图" min-width="180">-->
        <!--<template slot-scope="scope">-->
        <!--<div class="img_box">-->
        <!--<img v-if="scope.row.pics" class="img" v-for="(src,idx) in JSON.parse(scope.row.pics)" :src="src" :alt="idx" :key="idx">-->
        <!--<span v-if="!scope.row.pics">暂无</span>-->
        <!--</div>-->
        <!--</template>-->
        <!--</el-table-column>-->
        <el-table-column prop="description" label="户型概述" :formatter="defValFilter01" min-width="180"/>
        <el-table-column label="操作" width="180" fixed="right">
          <template slot-scope="scope">
            <el-button type="edit" size="small" @click="edit(scope.row)">编辑</el-button>
            <el-button type="del" size="small" @click="del(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </TablePage>
    <el-dialog :title="houseType.title" :modal="false" customClass="my-dialog" :visible.sync="houseType.show" width="1200px">
      <el-form :model="houseType" ref="houseType" labelWidth="120px">
        <el-form-item label="户型名称：" :rules="[{required: true, message: '请输入户型名称...', trigger: 'blur'}]">
          <el-input v-model="houseType.name" clearable placeholder="请输入户型名称..."/>
        </el-form-item>
        <el-form-item label="面积(㎡)：" :rules="[{required: true, message: '请输入面积...', trigger: 'blur'}]">
          <el-input-number v-model="houseType.acreage" :step="2" :min="0" :max="999" placeholder="请输入面积..."/>
        </el-form-item>
        <el-form-item label="户型规格：" :rules="[{required: true, message: '请输入户型规格...', trigger: 'blur'}]">
          <el-input v-model="houseType.spec" clearable placeholder="请输入户型规格..."/>
        </el-form-item>
        <el-form-item label="户型概述：" :rules="[{required: true, message: '请输入户型概述...', trigger: 'blur'}]">
          <el-input type="textarea" v-model="houseType.description" :rows="3" clearable placeholder="请输入户型概述..."/>
        </el-form-item>
        <el-form-item label="户型图：" :rules="[{required: true, message: '请上传户型图...', trigger: 'blur'}]">
          <MyUpload :files="houseType.picList" width="160" height="100" :length="6" @success="upload"/>
        </el-form-item>
      </el-form>
      <div class="text-center">
        <el-button @click="houseType.show = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {addHouseTypeApi, deleteHouseTypeApi, listHouseTypeApi, updateHouseTypeApi} from '../../api/houseApi'
  import {defValFilter01, acreageFilter} from '../../constant/filter'
  import {confirm, error, success} from '../../actions'
  import Breadcrumb from '../../components/Breadcrumb.vue'
  import TablePage from '../../components/TablePage.vue'
  import MyUpload from '../../components/MyUpload.vue'

  export default {
    name: 'HouseType',
    components: {Breadcrumb, TablePage, MyUpload},
    data() {
      return {
        data: {list: [], loading: false},
        houseType: {name: '', acreage: 0.0, description: '', spec: '', pics: '', picList: [], title: '添加户型', show: false},
      }
    },
    methods: {
      defValFilter01,
      acreageFilter,
      upload(pics) {
        this.houseType.picList = pics
        this.houseType.pics = JSON.stringify(pics)
      },
      findList() {
        this.data.loading = true
        listHouseTypeApi().then((list) => {
          this.data = {list, loading: false}
        })
      },
      add() {
        this.houseType = {name: '', acreage: 0.0, description: '', spec: '', pics: '', picList: [], title: '添加户型', show: true}
      },
      edit(data) {
        this.houseType = {...data, picList: [], title: `修改户型【${data.name}】`, show: true}
        if (data.pics) {
          setTimeout(() => {
            this.houseType.picList = JSON.parse(data.pics)
          }, 10)
        }
      },
      save() {
        this.$refs.houseType.validate((valid) => {
          if (valid) {
            const fn = this.houseType.id ? updateHouseTypeApi(this.houseType) : addHouseTypeApi(this.houseType)
            fn.then(() => {
              this.houseType.show = false
              this.findList()
            })
          } else {
            return false
          }
        })
      },
      del({id, name}) {
        confirm(`确认删除户型【${name}】吗?`, '删除确认', 'warning').then(() => deleteHouseTypeApi(id).then(() => {
          success(`删除户型【${name}】成功！`)
          this.findList()
        }).catch(({msg}) => error(msg || '删除失败！请稍后重试...')))
      },
    },
    created() {
      this.findList()
    },
  }
</script>
