<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height:100%">
      <el-menu
          :default-openeds="['1']"

      >
        <el-submenu index="1" >
          <template slot="title"><i class="el-icon-message"></i>试运行</template>  <!--导航1-->
          <el-menu-item-group>
            <el-menu-item index="all" @click="productPath">全部产品</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group >
            <el-menu-item index="火炉">火炉</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="制冰机" >制冰机</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group >
            <el-menu-item index="冰箱" >冰箱</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group >
            <el-menu-item index="酒柜" >酒柜</el-menu-item>
          </el-menu-item-group>
          <!--          <el-submenu index="1-4">-->
          <!--            <template slot="title">选项4</template>-->
          <!--            <el-menu-item index="1-4-1">选项4-1</el-menu-item>-->
          <!--          </el-submenu>-->
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>订单</template> <!--导航2-->
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="订单(demo)">订单(demo)</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-setting"></i>供应商</template> <!--导航3-->
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="3-1">选项1</el-menu-item>
            <el-menu-item index="3-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="3-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style=" font-size: 12px">
        <el-dropdown style="float: right">
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
          <span>gsw</span>
        </el-dropdown>

      </el-header>

      <el-main>
        <div style="padding: 10px 0">
          <el-input type="text" style="width: 300px" placeholder="请按sku查询"  v-model="sku" @keyup.native.enter="load"></el-input><el-button type="primary" @click="load">搜索</el-button>
        </div>


        <el-table :data="tableData">




        </el-table> <!-- 主表单数据展示界面 -->



        <div  >
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[10, 20, 40, 80]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total=total>
          </el-pagination>
        </div><!-- 分页设置 -->
      </el-main>
    </el-container><!-- 右上角gsw界面 待完成 -->
  </el-container>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import axios, {request} from "axios";


export default {
  name: 'HomeView' ,
  components: {
    HelloWorld
  },
  data() {
    return {
      tableData: [],
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      pageSize:10,
      pageNum:1,
    }
  },
  created: function () {
    //请求分页查询所需的数据

  },
  methods: {

    handleSizeChange(pageSize) {
      //console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      //console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    productPath(){
      this.$router.push('/admin');
    }

  }

}
</script>
