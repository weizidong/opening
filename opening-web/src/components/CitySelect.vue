<template>
  <el-select v-model="city" :multiple="multiple" filterable remote :placeholder="placeholder" :remote-method="findList" :loading="data.loading" @change="change">
    <el-option v-for="item in data.list" :key="item.qccId" :label="item.qccName" :value="item[filed]"/>
  </el-select>
</template>
<script>
  import {getProviceApi} from '../api/universityApi'

  export default {
    name: 'city_select',
    data() {
      return {
        data: {page: 1, pageSize: 10, list: [], qccName: null, loading: false},
        city: null,
      }
    },
    props: {
      multiple: {
        type: Boolean,
        default: false,
      },
      placeholder: {
        type: String,
        default: '请输入城市名称',
      },
      filed: {
        type: String,
        default: 'qccName',
      },
    },
    methods: {
      findList(qccName) {
        this.data.loading = true
        getProviceApi({...this.data, qccName}).then(({list}) => {
          this.data = {...this.data, list, loading: false}
        })
      },
      change() {
        this.$emit('change', this.city)
      },
    },
  }
</script>
