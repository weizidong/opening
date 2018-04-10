<template>
  <div class="content_data">
    // 所有客户列表
    <Breadcrumb/>
    <TablePage>
      <el-table :data="data.list" v-loading="data.loading" element-loading-text="拼命加载中...">
        <el-table-column prop="name" label="姓名" :formatter="defValFilter02" min-width="100"/>
        <el-table-column prop="phone" label="电话" :formatter="defValFilter02" min-width="140"/>
        <el-table-column prop="address" label="家庭住址" :formatter="defValFilter02" min-width="180"/>
        <el-table-column prop="idNumber" label="身份证" :formatter="defValFilter02" min-width="180"/>
        <el-table-column prop="buildingNo" label="楼栋号" :formatter="defValFilter01" min-width="180"/>
        <el-table-column prop="floorNo" label="楼层" :formatter="defValFilter01" min-width="180"/>
        <el-table-column prop="roomNo" label="房号" :formatter="defValFilter01" min-width="180"/>
        <el-table-column prop="totalPrice" label="总价" :formatter="defValFilter03" min-width="180"/>
      </el-table>
    </TablePage>
    <MyPagination :page="data.page" :size="data.pageSize" :method="findList" :total="data.total"/>
  </div>
</template>

<script>
  import {getUserHouseApi, exportUserHouseApi} from '../../api/userApi'
  import {defValFilter01, defValFilter02, defValFilter03} from '../../constant/filter'
  import MyPagination from '../../components/MyPagination.vue'
  import Breadcrumb from '../../components/Breadcrumb.vue'
  import TablePage from '../../components/TablePage.vue'
  import ExportButton from '../../components/ExportButton.vue'

  export default {
    name: 'allUserList',
    components: {MyPagination, Breadcrumb, TablePage, ExportButton},
    data() {
      return {
        data: {page: 1, pageSize: 10, list: [], total: 0, loading: false},
      }
    },
    methods: {
      defValFilter01,
      defValFilter02,
      defValFilter03,
      exportUserHouseApi,
      findList(page = {}) {
        this.data.loading = true
        getUserHouseApi({...this.data, ...page}).then(({total, list}) => {
          this.data = {list, total, ...page, loading: false}
        })
      },
    },
    created() {
    },
  }
</script>
