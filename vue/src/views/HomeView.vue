<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height:100%">
      <el-menu
          :default-openeds="['1']"

      >
        <el-submenu index="1" >
          <template slot="title"><i class="el-icon-message"></i>试运行</template>  <!--导航1-->
          <el-menu-item-group>
            <el-menu-item index="all" @click="selectProductType('')">全部产品</el-menu-item>
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
        <template>
          <div class="container">
            <div class="select-container">
              <select class="selectOption1" v-model="productType">
                <option value="">全部产品</option>
                <option value="制冰机">制冰机</option>
                <option value="火炉">火炉</option>
                <option value="冰箱">冰箱</option>
                <option value="酒柜">酒柜</option>
              </select>
            </div>
            <div class="input-container">
              <el-input type="text" style="width: 300px" placeholder="请按sku查询" v-model="sku" @keyup.native.enter="load"></el-input>
              <el-button type="primary" @click="load">搜索</el-button>
            </div>
          </div>
        </template>

        <div>
          <template>
            <div >
              <el-button style="float: right" type="primary" @click="openDialog">添加新产品</el-button>
              <el-dialog :visible.sync="dialogVisible" title="添加产品">

                <el-input class="el_input1" v-model="skuSubmit" placeholder="sku"></el-input>
                <el-input class="el_input1" v-model="sku13Submit" placeholder="sku13"></el-input>
                <el-input class="el_input1" v-model="colorSubmit" placeholder="颜色"></el-input>
                <el-input class="el_input1" v-model="weight1Submit" placeholder="净重"></el-input>
                <el-input class="el_input1" v-model="weight2Submit" placeholder="毛重"></el-input>
                <el-input class="el_input1" v-model="chicun1Submit" placeholder="产品尺寸"></el-input>
                <el-input class="el_input1" v-model="chicun2Submit" placeholder="包装尺寸"></el-input>
                <el-input class="el_input1" v-model="inputSubmit" placeholder="功率"></el-input>
                <el-input class="el_input1" v-model="quantitySubmit" placeholder="装柜量"></el-input>
                <el-input class="el_input1" v-model="typeSubmit" placeholder="类型"></el-input>
                <el-upload
                    ref="upload"
                    class="upload-demo"
                    :on-success="handleUploadSuccess"
                    :before-upload="beforeUpload"
                    :auto-upload="false"
                >
                  <el-button slot="trigger" size="small" type="primary">选择产品图片</el-button>
                  <p>注:仅支持JPG/PNG且图片大小不得超过4MB</p>
                </el-upload>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取消</el-button>
                  <el-button type="primary" @click="submitForm">提交</el-button>
                </div>
              </el-dialog>
            </div>
          </template>

        </div> <!-- 添加新产品界面 -->
        <el-table :data="tableData">

          <el-table-column label="图片" prop="url">
            <template slot-scope="scope">
              <el-popover placement="top-start" title="" trigger="hover">
                <img :src=getUrl(scope.row) alt="" style="width: 800px;height: 800px">
                <img slot="reference" :src=getUrl(scope.row) style="width: 150px;height: 150px">
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column prop="sku" label="SKU" width="140">
          </el-table-column>
          <el-table-column prop="sku13" label="13位SKU编码" width="130">
          </el-table-column>
          <el-table-column prop="chicun1" label="产品尺寸">
          </el-table-column>
          <el-table-column prop="chicun2" label="包装尺寸">
          </el-table-column>
          <el-table-column prop="color" label="颜色">
          </el-table-column>
          <el-table-column prop="weight1" label="净重">
          </el-table-column>
          <el-table-column prop="weight2" label="毛重">
          </el-table-column>
          <el-table-column prop="input" label="输入功率">
          </el-table-column>
          <el-table-column prop="quantityHQ" label="装柜量(40HQ)">
          </el-table-column>

          <el-table-column label="操作" width="80">
            <template slot-scope="scope">
              <el-button type="primary" @click="handleEdit(scope.row)">修改</el-button>
            </template>
          </el-table-column>

          <!-- 删除按钮列 -->
          <el-table-column label="操作" width="80">
            <template slot-scope="scope">
              <el-button type="danger" @click="confirmDelete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table> <!-- 主表单数据展示界面 -->

        <div>
          <el-dialog  title="修改参数" :visible.sync="dislogVisibleUpdate" >
            <div><p>填入需要修改的新值 无需修改的字段请空着</p></div>
            <el-input class="el_input1" v-model="skuUpdate" placeholder="sku"></el-input>
            <el-input class="el_input1" v-model="sku13Update" placeholder="sku13"></el-input>

            <el-input class="el_input1" v-model="colorUpdate" placeholder="颜色"></el-input>
            <el-input class="el_input1" v-model="weight1Update" placeholder="净重"></el-input>
            <el-input class="el_input1" v-model="weight2Update" placeholder="毛重"></el-input>
            <el-input class="el_input1" v-model="chicun1Update" placeholder="产品尺寸"></el-input>
            <el-input class="el_input1" v-model="chicun2Update" placeholder="包装尺寸"></el-input>
            <el-input class="el_input1" v-model="inputUpdate" placeholder="功率"></el-input>
            <el-input class="el_input1" v-model="quantityUpdate" placeholder="装柜量"></el-input>
            <el-input class="el_input1" v-model="typeUpdate" placeholder="类型"></el-input>
            <div slot="footer" class="dialog-footer">
              <el-button @click="cancelUpdate">取消</el-button>
              <el-button type="primary" @click="saveChanges">保存</el-button>
            </div>
          </el-dialog>
        </div><!-- 修改按钮部分界面 -->

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
      sku:'',
      productType:'',
      dialogVisible: false, //添加按钮的弹出
      dislogVisibleUpdate: false,//修改按钮的弹出
      file: null,
      skuSubmit:'',
      colorSubmit:'',
      chicun1Submit:'',
      chicun2Submit:'',
      weight1Submit:'',
      weight2Submit:'',
      inputSubmit:'',
      quantitySubmit:'',
      sku13Submit:'',
      typeSubmit:'',
      skuUpdate:'',
      sku13Update:'',
      colorUpdate:'',
      chicun1Update:'',
      chicun2Update:'',
      weight1Update:'',
      weight2Update:'',
      inputUpdate:'',
      quantityUpdate:'',
      typeUpdate:'',
      currentUpdateData:{
        currentUpdateProductId:'', //获取修改时当前行的id
        currentUpdateSku:'',
        currentUpdateSku13:'',
        currentUpdateColor:'',
        currentUpdateChicun1:'',
        currentUpdateChicun2:'',
        currentUpdateWeight1:'',
        currentUpdateWeight2:'',
        currentUpdateInput:'',
        currentUpdateQuantity:'',
        currentUpdateType:'',
      }
    }
  },
  watch: {
    productType(newType) {
      this.selectProductType(newType);
    }
  },
  created: function () {
    //请求分页查询所需的数据
    this.load();
  },
  methods: {
    load() {
      let url =
          "http://192.168.40.243:9090/product/page?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize +
          "&sku=" +
          this.sku +
          "&type=" +
          encodeURIComponent(this.productType);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.tableData = res.data;
            this.total = res.total;
          });


    },
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
    getUrl(row) {
      //console.log(row.url)
      return `/image/${row.url}`;
    },
    selectProductType(type) {
      this.productType = type;
      this.pageNum = 1;
      this.load();
    },
    openDialog() {
      // 打开对话框
      this.dialogVisible = true;
    },
    handleUploadSuccess(response, file, fileList) {
      // 上传成功的回调函数
      // 保存上传的文件
      this.file = file;
    },
    beforeUpload(file) {
      // 限制上传的文件类型和大小
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 4;

      if (!isJPG || !isLt2M) {
        this.$message.error('上传图片只能是 JPG 或 PNG 格式，且不超过 4MB!');
        return false;
      }
      this.file = file;
      return true;
    },
    submitForm() {
      // 构造表单数据
      const formData = new FormData();
      const file = this.$refs.upload.uploadFiles[0];
      formData.append('file', file.raw);

      //formData.append('file', this.file);
      formData.append('sku', this.skuSubmit);
      formData.append('color', this.colorSubmit);
      formData.append('chicun1', this.chicun1Submit);
      formData.append('chicun2', this.chicun2Submit);
      formData.append('weight1', this.weight1Submit);
      formData.append('weight2', this.weight2Submit);
      formData.append('quantity', this.quantitySubmit);
      formData.append('sku13', this.sku13Submit);
      formData.append('type', this.typeSubmit);
      formData.append('input', this.inputSubmit);
      //this.$refs.upload.submit();
      console.log([...formData.entries()]);
      // 发送表单数据到后端保存
      axios.post('/api/saveData', formData)
          .then(response => {
            // 处理保存成功的逻辑
            this.$message.success('数据保存成功！');
            // 关闭对话框
            this.dialogVisible = false;
            this.load();
          })
          .catch(error => {
            // 处理保存失败的逻辑
            this.$message.error('数据保存失败！');
          });
    },
    handleEdit(row) {
      // 处理修改按钮点击事件
      console.log(row)
        this.dislogVisibleUpdate = true;
        this.currentUpdateData.currentUpdateProductId = row.id;
        this.currentUpdateData.currentUpdateSku = row.sku;
        this.currentUpdateData.currentUpdateSku13 = row.sku13;
        this.currentUpdateData.currentUpdateColor = row.color;
        this.currentUpdateData.currentUpdateChicun1 = row.chicun1;
        this.currentUpdateData.currentUpdateChicun2 = row.chicun2;
        this.currentUpdateData.currentUpdateWeight11 = row.weight1;
        this.currentUpdateData.currentUpdateWeight12 = row.weight2;
        this.currentUpdateData.currentUpdateQuantity = row.quantityHQ;
        this.currentUpdateData.currentUpdateType = row.type;


        //console.log(this.currentUpdateProductId);
    },
    confirmDelete(id) {
      this.$confirm("确定要删除吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(() => {
            // 用户点击了确定按钮，执行删除操作
            console.log(id);
            this.deleteItem(id);
          })
          .catch(() => {
            // 用户点击了取消按钮，不执行删除操作
          });
    },
    deleteItem(id) {
      // 发送删除请求到后端
      axios.delete(`/product/api/delete/${id}`)
          .then(response => {
            // 删除成功
            this.load();
          })
          .catch(error => {
            // 删除失败
            console.error(error);
          });
    },
    cancelUpdate() {
      // 取消修改，关闭对话框并清空输入框的值
      this.dislogVisibleUpdate = false;
      this.clearFields();
    },
    clearFields() {
      // 修改界面每次清空
      this.skuUpdate = '';
      this.sku13Update = '';
      this.colorUpdate = '';
      this.weight1Update = '';
      this.weight2Update = '';
      this.chicun1Update = '';
      this.chicun2Update = '';
      this.inputUpdate = '';
      this.quantityUpdate = '';
      this.typeUpdate = '';
      this.currentUpdateProductId = '';
    },
    saveChanges(id) {   //修改界面提交按钮函数

      const updateProductData = new FormData();
      updateProductData.append('id',this.currentUpdateData.currentUpdateProductId);
      updateProductData.append('skuOld',this.currentUpdateData.currentUpdateSku);
      updateProductData.append('sku13Old',this.currentUpdateData.currentUpdateSku13);
      updateProductData.append('colorOld',this.currentUpdateData.currentUpdateColor);
      updateProductData.append('weight1Old',this.currentUpdateData.currentUpdateWeight1);
      updateProductData.append('weight2Old',this.currentUpdateData.currentUpdateWeight2);
      updateProductData.append('chicun1Old',this.currentUpdateData.currentUpdateChicun1);
      updateProductData.append('chicun2Old',this.currentUpdateData.currentUpdateChicun2);
      updateProductData.append('inputOld',this.currentUpdateData.currentUpdateInput);
      updateProductData.append('quantityOld',this.currentUpdateData.currentUpdateQuantity);
      updateProductData.append('typeOld',this.currentUpdateData.currentUpdateType);

      updateProductData.append('skuNew',this.skuUpdate);
      updateProductData.append('sku13New',this.sku13Update);
      updateProductData.append('colorNew',this.colorUpdate);
      updateProductData.append('weight1New',this.weight1Update);
      updateProductData.append('weight2New',this.weight2Update);
      updateProductData.append('chicun1New',this.chicun1Update);
      updateProductData.append('chicun2New',this.chicun2Update);
      updateProductData.append('inputNew',this.inputUpdate);
      updateProductData.append('quantityNew',this.quantityUpdate);
      updateProductData.append('typeNew',this.typeUpdate);
      console.log([...updateProductData.entries()]);

      // const params = {
      //   sku: this.skuUpdate || null,
      //   sku13: this.sku13Update || null,
      //   color: this.colorUpdate || null,
      //   weight1: this.weight1Update || null,
      //   weight2: this.weight2Update || null,
      //   chicun1: this.chicun1Update || null,
      //   chicun2: this.chicun2Update || null,
      //   input: this.inputUpdate || null,
      //   quantity: this.quantityUpdate || null,
      //   type: this.typeUpdate || null
      // };

      axios.post(`/product/api/updateProduct`, updateProductData)
          .then(response => {
            // 修改成功，关闭对话框并清空输入框的值
            this.dislogVisibleUpdate = false;
            this.clearFields();
            this.load();
            // 处理其他逻辑
          })
          .catch(error => {
            // 处理错误
            console.error(error);
          });
    },
    orderDemo(){
      console.log("我点击了切换界面按钮")
      this.$router.push('/orderDemo');
    },

  }

}
</script>
