<template>
  <div class="app-container">
    <el-form
      ref="modelForm"
      :rules="rules"
      :model="modelForm"
      label-position="right"
      label-width="80px"
    >
      <el-form-item label="模型名称" prop="name">
        <el-input v-model="modelForm.name" placeholder="请输入模型名称" />
      </el-form-item>
      <el-form-item label="模型标识" prop="key">
        <el-input v-model="modelForm.key" placeholder="请输入模型标识" />
      </el-form-item>
      <el-form-item label="流程描述" prop="desc">
        <el-input v-model="modelForm.desc" type="textarea" :rows="2" placeholder="请输入流程描述" />
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'ModelEdit',
  props: {
    modelId: {
      required: false,
      type: String,
      default: ''
    }
  },
  data() {
    return {
      props: {
        value: 'id',
        label: 'name'
      },
      modelForm: {
        name: '',
        key: '',
        category: '',
        desc: ''
      },
      rules: {
        name: [
          { required: true, message: '模型名称不能为空', trigger: 'change' }
        ],
        key: [
          { required: true, message: '模型标识不能为空', trigger: 'change' }
        ]
      }
    }
  },
  mounted() {
  },
  methods: {
    submitForm(fn) {
      const _this = this
      this.$refs['modelForm'].validate(valid => {
        if (valid) {
          _this.axios.post('http://localhost:8011/activiti/model/save',this.modelForm).then(function (resp) {
            if (resp.status===200) {
              _this.$alert(_this.modelForm.name + '保存成功', 'OK', {
                confirmButtonText: '确定',
                callback: action => {
                  self.showDialog = false
                  // self.queryList()
                  window.location.reload()
                }
              });
            }
          })

        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm() {
      this.$refs['modelForm'].resetFields()
    },
    getValue(val) {
      this.modelForm.category = val
    }
  }
}
</script>
