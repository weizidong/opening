<template>
  <div class="content_data">
    <Breadcrumb/>
    <el-button type="primary" icon="el-icon-plus" @click="$router.push({name: 'roleEdit', params: {id: CREATE}})">添加角色</el-button>
    <el-table :data="data.list" v-loading="data.loading" element-loading-text="拼命加载中...">
      <el-table-column prop="name" label="角色名称" width="100"/>
      <el-table-column prop="tips" label="提示" :formatter="defValFilter01" width="140"/>
      <el-table-column prop="menus" label="权限" :formatter="formatMenusFilter" min-width="240"/>
      <el-table-column label="操作" width="100" fixed="right">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="$router.push({name: 'roleEdit', params: {id:scope.row.id}})">编辑</el-button>
          <el-button type="text" size="small" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <MyPagination :page="data.page" :size="data.pageSize" :method="findList" :total="data.total"/>
  </div>
</template>

<script>
  import {getRoleListApi, delRoleApi} from '../../../api/roleApi'
  import {defValFilter01} from '../../../constant/filter'
  import {success, error, confirm} from '../../../actions'
  import {CREATE} from '../../../constant'
  import MyPagination from '../../../components/MyPagination.vue'
  import Breadcrumb from '../../../components/Breadcrumb.vue'
  export default {
    name: 'roleList',
    components: {MyPagination, Breadcrumb},
    data() {
      return {
        CREATE,
        data: {page: 1, pageSize: 10, list: [], total: 0, loading: false},
      }
    },
    methods: {
      defValFilter01,
      formatMenusFilter({menus = ['无']}) {
        return menus.map(m => m.name).join('，')
      },
      findList(page = {}) {
        this.data.loading = true
        getRoleListApi({...this.data, ...page}).then(({total, list}) => {
          this.data = {list, total, ...page, loading: false}
        })
      },
      del({id, name}) {
        confirm(`确认删除角色"${name}"?`, 'warning', '删除确认').then(() => delRoleApi(id).then(() => {
          success(`删除角色"${name}"成功！`)
          this.findList()
        }).catch(({msg}) => error(msg || '删除失败！请稍后重试...')))
      },
    },
  }
</script>
