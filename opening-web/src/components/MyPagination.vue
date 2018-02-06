<template>
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="page"
    :page-sizes="[10, 20, 50, 100]"
    :page-size="pageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total">
  </el-pagination>
</template>
<script>
  export default{
    name: 'myPagination',
    props: {
      method: Function,
      pageSize: {
        type: Number,
        default: 10,
      },
      page: {
        type: Number,
        default: 1,
      },
      total: {
        type: Number,
        default: 0,
      },
    },
    methods: {
      handleSizeChange (pageSize) {
        if (!pageSize) {
          return
        }
        this.$router.push({query: {...this.query, pageSize}})
        this.method && this.method({page: this.page, pageSize})
      },
      handleCurrentChange (page) {
        if (!this.pageSize) {
          return
        }
        this.$router.push({query: {...this.query, page}})
        this.method && this.method({page, pageSize: this.pageSize})
      },
    },
    created: function () {
      const {page = 1, pageSize = 10} = this.$route.query
      this.method && this.method({page: page * 1, pageSize: pageSize * 1})
    },
  }
</script>
