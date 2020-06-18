<template>
  <div>
    <el-table
      v-loading="loading"
      :data="data"
      fit
      stripe
      highlight-current-row
      style="width: 100%;"
      :header-cell-style="{background:'#eef1f6',color:'#606266'}"
      @selection-change="onSelectionChange"
    >
      <template v-for="item in columns">
        <slot v-if="item.slot" :name="item.field" />
        <el-table-column
          v-else-if="item.type === 'index'"
          :key="item.label"
          :label="item.label"
          :type="item.type"
          :width="item.width"
          align="center"
        >
          <template scope="scope">
            <!-- 有分页时，序号显示 -->
            <span v-if="query">{{ (query.pageNum - 1)*query.pageSize + scope.$index + 1 }}</span>
            <!-- 无分页时，序号显示 -->
            <span v-else>{{ scope.$index + 1 }}</span>
          </template>
        </el-table-column>
        <!-- 多选框 -->
        <el-table-column
          v-else-if="item.type === 'selection'"
          :key="item.type"
          :type="item.type"
          :width="item.width"
        />
        <el-table-column
          v-else
          :key="item.field"
          :fixed="fixed"
          :label="item.label"
          :prop="item.field"
          :align="item.align"
          :min-width="item.minWidth"
          :formatter="item.formatter"
        />
      </template>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="query.pageNum"
      :limit.sync="query.pageSize"
      @pagination="pagination"
    />
  </div>
</template>

<script>
import Pagination from '../../components/Pagination/index'
export default {
  name: 'Table',
  components: { Pagination },
  props: {
    loading: {
      type: Boolean,
      default: true
    },
    fixed: {
      type: Boolean,
      default: false
    },
    data: {
      type: Array,
      default: () => {
        return []
      }
    },
    columns: {
      type: Array,
      default: () => {
        return []
      }
    },
    total: {
      type: Number,
      default: 0
    },
    query: {
      type: Object,
      default: () => {
        return {
          pageNum: 1,
          pageSize: 10
        }
      }
    }
  },
  data() {
    return {
    }
  },
  methods: {
    pagination() {
      this.$emit('pagination')
    },
    onSelectionChange(val) {
      this.$emit('onSelectionChange', val)
    }
  }
}
</script>

<style>
</style>
