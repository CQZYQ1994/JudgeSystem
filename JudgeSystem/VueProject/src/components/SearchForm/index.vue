<template>
  <div style="width: 100%">
    <el-col :span="8">
      <el-form :inline="true">
        <el-form-item v-for="item in formOptions.toolBar" :key="item.field">
          <el-button
            v-if="item.field === 'add'"
            :type="item.btnType"
            :icon="item.icon"
            @click="handleCreate"
          >{{ item.name }}</el-button>
          <el-button
            v-if="item.field === 'delete'"
            style="margin-left: 10px;"
            :type="item.btnType"
            :icon="item.icon"
            :disabled="selectKeys.length<=0"
            @click="handleDelete(selectKeys)"
          >{{ item.name }}</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="16">
      <div class="search-box">
        <el-form :inline="inline">
          <el-form-item v-for="item in formOptions.formItems" :key="item.field" :label="item.label">
            <el-input
              v-if="item.type === 'input'"
              v-model="formOptions.formData[item.field]"
              :placeholder="item.placeholder"
            />
            <el-button
              v-if="item.type === 'button'&& item.field === 'search'"
              v-waves
              type="primary"
              icon="el-icon-search"
              @click="clickButton"
            >{{ item.name }}</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
  </div>
</template>

<script>
import waves from '../../directive/waves'
export default {
  name: 'SearchForm',
  directives: { waves },
  props: {
    inline: {
      type: Boolean,
      default: true
    },
    formOptions: {
      type: Object,
      default: () => {
        return []
      }
    },
    selectKeys: {
      type: Array,
      default: () => {
        return []
      }
    },
    create: {
      type: Function,
      default: null
    },
    delete: {
      type: Function,
      default: null
    }
  },
  data() {
    return {

    }
  },
  methods: {
    clickButton() {
      this.$emit('search', this.formOptions.formData)
    },
    handleCreate() {
      this.create && this.create()
    },
    handleDelete(keys) {
      this.delete && this.delete(keys)
    }
  }
}
</script>

<style scoped>
.search-box {
  float: right;
}
</style>
