<template>
  <div class="content_data">
    <Breadcrumb/>
    <el-button type="primary" icon="el-icon-plus" @click="$router.push({name: 'adminEdit', params: {id: CREATE}})">添加管理员</el-button>
    <TablePage>
      <el-table :data="data.list" v-loading="data.loading" element-loading-text="拼命加载中...">
        <el-table-column prop="avatar" label="头像" min-width="100">
          <template slot-scope="scope">
            <HeadImg :src="scope.row.avatar"/>
          </template>
        </el-table-column>
        <el-table-column prop="account" label="账号" min-width="100"/>
        <el-table-column prop="name" label="姓名" sortable :formatter="defValFilter02" min-width="140"/>
        <el-table-column prop="email" label="Email" :formatter="defValFilter01" min-width="180"/>
        <el-table-column prop="phone" label="电话" :formatter="defValFilter01" min-width="180"/>
        <el-table-column prop="logintime" label="最近登录时间" :formatter="dateTimeFilter" min-width="180"/>
        <el-table-column label="状态" min-width="140" fixed="right">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ff4949" active-text="启用" inactive-text="禁用" :active-value="1" :inactive-value="2" @change="statusChange(scope.row)"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template slot-scope="scope">
            <el-button type="edit" size="small" @click="$router.push({name: 'adminEdit', params: {id:scope.row.id}})">编辑</el-button>
            <el-button type="del" size="small" @click="del(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </TablePage>
    <MyPagination :page="data.page" :size="data.pageSize" :method="findList" :total="data.total"/>
  </div>
</template>

<script>
  import {findAdminListApi, delAdminApi, toggleAdminApi} from '../../../api/adminApi'
  import {dateTimeFilter, defValFilter01, defValFilter02} from '../../../constant/filter'
  import {success, error, confirm} from '../../../actions'
  import {CREATE} from '../../../constant'
  import MyPagination from '../../../components/MyPagination.vue'
  import Breadcrumb from '../../../components/Breadcrumb.vue'
  import HeadImg from '../../../components/HeadImg.vue'
  import TablePage from '../../../components/TablePage.vue'

  export default {
    name: 'adminList',
    components: {MyPagination, HeadImg, Breadcrumb, TablePage},
    data() {
      return {
        CREATE,
        data: {page: 1, pageSize: 10, list: [], total: 0, loading: false},
      }
    },
    methods: {
      dateTimeFilter,
      defValFilter01,
      defValFilter02,
      findList(page = {}) {
        this.data.loading = true
        findAdminListApi({...this.data, ...page}).then(({total, list}) => {
          this.data = {list, total, ...page, loading: false}
        })
      },
      statusChange({id, account, status}) {
        toggleAdminApi(id).then(() => {
          success(`${status - 1 ? '禁用' : '启用'}账号"${account}"成功！`)
        }).catch(({msg}) => error(msg || `${status ? '禁用' : '启用'}失败！请稍后重试...`))
      },
      del({id, account}) {
        confirm(`确认删除账号"${account}"?`, 'warning', '删除确认').then(() => delAdminApi(id).then(() => {
          success(`删除账号"${account}"成功！`)
          this.findList()
        }).catch(({msg}) => error(msg || '删除失败！请稍后重试...')))
      },
    },
    created() {
    },
  }
</script>
