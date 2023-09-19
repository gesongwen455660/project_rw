<template >
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height:100%">
      <el-menu
          :default-openeds="['1']"
      >
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>试运行</template>  <!--导航1-->
          <el-menu-item-group>
            <el-menu-item index="all" @click="toHome">全部产品</el-menu-item>
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
            <el-menu-item index="订单(demo)" @click="orderDemo">订单(demo)</el-menu-item>
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
            <el-menu-item index="3-1" @click="supplierPage">具体信息</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header  style=" font-size: 12px">
        <el-dropdown style="float: right">
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
          <span>gsw</span>
        </el-dropdown>
        <p style="font-size: 40px"> </p>
      </el-header>

      <el-main>
        <template>
          <div class="container">
            <div class="input-container">
              <el-input type="text" style="width: 300px" placeholder="供应商" v-model="supplierName" @keyup.native.enter="load"></el-input>
              <el-button type="primary" @click="load">搜索</el-button>
              <el-button style="float: right" type="primary" @click="openAddDataDialog">添加新供应商</el-button>
            </div>
          </div>
        </template>
        <!-- 主表格  -->
        <div >
          <el-table :data="tableData" class="custom-table dragscroll" :scroll-x="true">
            <el-table-column width="200px"  prop="supplierName" label="供应商名称"></el-table-column>
            <el-table-column width="200px" prop="supplierId" label="供应商代码"></el-table-column>
            <el-table-column width="200px" prop="productType" label="产品类别"></el-table-column>
            <el-table-column width="200px" prop="supplierAddress" label="供应商地址"></el-table-column>
            <el-table-column width="200px" prop="relationMan" label="联系人"></el-table-column>
            <el-table-column width="200px" prop="zhiWu" label="职务"></el-table-column>
            <el-table-column width="200px" prop="phone" label="联系电话"></el-table-column>
            <el-table-column width="150px" prop="date" label="资格生效时间"></el-table-column>
            <!-- 删除按钮列 -->
            <el-table-column label="操作" width="80">
              <template slot-scope="scope">
                <el-button type="danger" @click="confirmDelete(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>


        <div >

          <el-dialog :visible.sync="dialogVisibleAddData" @close="cancelAddDataForm" title="添加供应商">

            <el-input class="el_input1" v-model="newSupplierData.supplierNameSubmit" placeholder="供应商名称"></el-input>
            <el-input class="el_input1" v-model="newSupplierData.supplierIdSubmit" placeholder="供应商代码"></el-input>
            <el-input class="el_input1" v-model="newSupplierData.productTypeSubmit" placeholder="产品类别"></el-input>
            <el-input class="el_input1" v-model="newSupplierData.supplierAddressSubmit" placeholder="供应商地址"></el-input>
            <el-input class="el_input1" v-model="newSupplierData.relationManSubmit" placeholder="联系人"></el-input>
            <el-input class="el_input1" v-model="newSupplierData.zhiWuSubmit" placeholder="职务"></el-input>
            <el-input class="el_input1" v-model="newSupplierData.phoneSubmit" placeholder="联系电话"></el-input>
            <el-input class="el_input1" v-model="newSupplierData.dateSubmit" placeholder="资格生效日期"></el-input>
            <div slot="footer" class="dialog-footer">
              <el-button @click="cancelAddDataForm">取消</el-button>
              <el-button type="primary" @click="submitAddDataForm">提交</el-button>
            </div>
          </el-dialog>
        </div>







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
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import dragscroll from 'dragscroll';
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
      dialogVisibleAddData: false,//添加供应商界面弹出
      supplierName: '',
      total: 0,
        newSupplierData:{
          supplierNameSubmit: '',
          supplierIdSubmit: '',
          productTypeSubmit: '',
          supplierAddressSubmit: '',
          relationManSubmit: '',
          zhiWuSubmit: '',
          phoneSubmit: '',
          dateSubmit: '',
        }


    }
  },
  mounted() {

  },
  created: function () {
    //请求分页查询所需的数据
    this.load();
  },
  watch: {

  },
  methods:{
    load() {
      // 发送请求获取数据
      axios.get('/supplier/page', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          supplierName: this.supplierName
        }
      })
          .then(response => {
            this.tableData = response.data.data;
            this.total = response.data.total;
          })
          .catch(error => {
            console.error('Error fetching data:', error);
          });
    },
    confirmDelete(id) {
      // 弹出确认框，确认是否删除
      this.$confirm('确定删除该供应商吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
          .then(() => {
            // 发送删除请求
            axios.delete(`/supplier/api/delete/${id}`)
                .then(response => {
                  // 删除成功后刷新表格数据
                  if (response.data > 0) {
                    this.load();
                    this.$message.success('删除成功');
                  } else {
                    this.$message.error('删除失败');
                  }
                })
                .catch(error => {
                  console.error('Error deleting data:', error);
                  this.$message.error('删除失败');
                });
          })
          .catch(() => {
            // 用户取消删除操作
          });
    },
    openAddDataDialog() {
      this.dialogVisibleAddData = true; // 打开添加供应商对话框
    },
    cancelAddDataForm() {
      this.dialogVisibleAddData = false; // 取消添加供应商表单
      this.resetFormData();
    },
    resetFormData() {
      // 重置表单数据
      this.newSupplierData = {
        supplierNameSubmit: '',
        supplierIdSubmit: '',
        productTypeSubmit: '',
        supplierAddressSubmit: '',
        relationManSubmit: '',
        zhiWuSubmit: '',
        phoneSubmit: '',
        dateSubmit: ''
      };
    },
    submitAddDataForm() {
      // 发送添加供应商请求
      axios.post('/supplier/saveData', this.newSupplierData)
          .then(response => {
            // 处理添加成功的逻辑
            this.dialogVisibleAddData = false;
            this.resetFormData();
            this.load(); // 刷新表格数据
            this.$message.success('添加供应商成功');
          })
          .catch(error => {
            console.error('Error adding data:', error);
            this.$message.error('添加供应商失败');
          });
    },
    toHome() {
      this.$router.push('/admin');
    },
  }



}
</script>
