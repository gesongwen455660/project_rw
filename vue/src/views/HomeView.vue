<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside  width="200px" style="background-color: rgb(238, 241, 246); height:100%">
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
            <el-menu-item index="3-1" @click="supplierPage">具体信息</el-menu-item>
          </el-menu-item-group>
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
            <div class="input-container">
              <el-input type="text" style="width: 300px" placeholder="请按工厂查询" v-model="factory" @keyup.native.enter="load"></el-input>
              <el-button type="primary" @click="load">搜索</el-button>
            </div>
          </div>
        </template>

        <div>
          <template>
            <div >
              <el-button style="float: right" type="primary" @click="openDialog">添加新产品</el-button>
              <el-dialog :visible.sync="dialogVisible" @close="cancelForm" title="添加产品">

                <el-input class="el_input1" v-model="newProductAddData.factorySubmit" placeholder="工厂"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.skuSubmit" placeholder="sku"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.fbaSubmit" placeholder="产品属性FBA"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.fbmSubmit" placeholder="产品属性FBM"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.fulfillmentfeeamazonSubmit" placeholder="亚马逊预估快递费"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.fulfillmentfeeoutseaSubmit" placeholder="海外仓预估快递费(基础运费+附加费)"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.productlenghcm1Submit" placeholder="产品尺寸CM 最长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.productlenghcm2Submit" placeholder="产品尺寸CM 次长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.productshortlenghcmSubmit" placeholder="产品尺寸CM 最短"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.outboxlenghcm1Submit" placeholder="外箱尺寸CM 最长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.outboxlenghcm2Submit" placeholder="外箱尺寸CM 次长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.outboxshortlenghcmSubmit" placeholder="外箱尺寸CM 最短"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.weightkg1Submit" placeholder="整箱重量KG 净重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.weightkg2Submit" placeholder="整箱重量KG 毛重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.productlenghinch1Submit" placeholder="产品尺寸INCH 最长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.productlenghinch2Submit" placeholder="产品尺寸INCH 次长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.productshortlenghinchSubmit" placeholder="产品尺寸INCH 最短"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.outboxlenghinch1Submit" placeholder="外箱尺寸INCH 最长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.outboxlenghinch2Submit" placeholder="外箱尺寸INCH 次长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.outboxshortlenghinchSubmit" placeholder="外箱尺寸INCH 最短"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.weightlb1Submit" placeholder="整箱重量LB 净重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.weightlb2Submit" placeholder="整箱重量LB 毛重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.quantityhqSubmit" placeholder="装柜量"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.quantitySubmit" placeholder="一箱几件"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.inboxlenghinch1Submit" placeholder="一箱多件内箱尺寸INCH 最长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.inboxlenghcm2Submit" placeholder="一箱多件内箱尺寸CM 次长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.inboxshortlenghcmSubmit" placeholder="一箱多件内箱尺寸CM 最短"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.inboxweightkg1Submit" placeholder="一箱多件内箱重量KG 净重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.inboxweightkg2Submit" placeholder="一箱多件内箱重量KG 毛重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.minlenghinchheightSubmit" placeholder="最小包装尺寸INCH 最小包装尺寸长"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.minlenghinchwidthSubmit" placeholder="最小包装尺寸INCH 最小包装尺寸宽"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.minlenghinchhighSubmit" placeholder="最小包装尺寸INCH 最小包装尺寸高"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.minweight1Submit" placeholder="最小包装尺寸净重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.minweight2Submit" placeholder="最小包装尺寸毛重"></el-input>
                <el-input class="el_input1" v-model="newProductAddData.typeSubmit" placeholder="产品类型"></el-input>



                <p>注:仅支持JPG/PNG且图片大小不得超过4MB</p>
                <p>注:务必分别上传，如果只在一个按钮上传只会上传一张</p>
                <el-upload
                    ref="upload1"
                    class="upload-demo"
                    :on-success="handleUploadSuccess"
                    :before-upload="beforeUpload"
                    :auto-upload="false"
                    style="margin-bottom: 20px"
                >
                  <el-button slot="trigger" size="small" type="primary">选择产品图片1</el-button>

                </el-upload>
                <el-upload
                    ref="upload2"
                    class="upload-demo"
                    :on-success="handleUploadSuccess"
                    :before-upload="beforeUpload"
                    :auto-upload="false"
                    style="margin-bottom: 20px"
                >
                  <el-button slot="trigger" size="small" type="primary">选择产品图片2</el-button>

                </el-upload>

                <el-upload
                    ref="upload3"
                    class="upload-demo"
                    :on-success="handleUploadSuccess"
                    :before-upload="beforeUpload"
                    :auto-upload="false"
                >
                  <el-button slot="trigger" size="small" type="primary">选择产品图片3</el-button>

                </el-upload>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="cancelForm">取消</el-button>
                  <el-button type="primary" @click="submitForm">提交</el-button>
                </div>
              </el-dialog>
            </div>
          </template>

        </div> <!-- 添加新产品界面 -->
        <el-table :data="tableData" class="custom-table">
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
          <el-table-column width="250px" label="图片" prop="url">
            <template slot-scope="scope">
              <el-popover placement="top-start" title="" trigger="click">
                <img :src=getUrl(scope.row,1) alt="" style="width: 400px;height: 400px">
                <img :src=getUrl(scope.row,2) alt="" style="width: 400px;height: 400px">
                <img :src=getUrl(scope.row,3) alt="" style="width: 400px;height: 400px">
                <img slot="reference" :src=getUrl(scope.row,1) style="width: 150px;height: 150px">
              </el-popover>
            </template>
          </el-table-column>

          <el-table-column width="100px" prop="factory" label="工厂" :filters="factoryFilters" :filter-method="filterMethod"></el-table-column>
          <el-table-column width="100px" prop="sku" label="sku" :filters="skuFilters" :filter-method="filterMethod"></el-table-column>
          <el-table-column width="150px" prop="fba" label="产品属性FBA"></el-table-column>
          <el-table-column width="150px" prop="fbm" label="产品属性FBM"></el-table-column>
          <el-table-column width="150px" prop="fulfillmentfeeamazon" label="亚马逊预估快递费"></el-table-column>
          <el-table-column width="150px" prop="fulfillmentfeeoutsea" label="海外仓预估快递费(基础运费+附加费)"></el-table-column>
          <el-table-column width="150px" prop="productlenghcm1" label="产品尺寸CM 最长"></el-table-column>
          <el-table-column width="150px" prop="productlenghcm2" label="产品尺寸CM 次长"></el-table-column>
          <el-table-column width="150px" prop="productshortlenghcm" label="产品尺寸CM 最短"></el-table-column>
          <el-table-column width="150px" prop="outboxlenghcm1" label="外箱尺寸CM 最长"></el-table-column>
          <el-table-column width="150px" prop="outboxlenghcm2" label="外箱尺寸CM 次长"></el-table-column>
          <el-table-column width="150px" prop="outboxshortlenghcm" label="外箱尺寸CM 最短"></el-table-column>
          <el-table-column width="200px" prop="weightkg1" label="整箱重量KG 净重"></el-table-column>
          <el-table-column width="200px" prop="weightkg2" label="整箱重量KG 毛重"></el-table-column>
          <el-table-column width="200px" prop="productlenghinch1" label="产品尺寸INCH 最长"></el-table-column>
          <el-table-column width="200px" prop="productlenghinch2" label="产品尺寸INCH 次长"></el-table-column>
          <el-table-column width="200px" prop="productshortlenghinch" label="产品尺寸INCH 最短"></el-table-column>
          <el-table-column width="200px" prop="outboxlenghinch1" label="外箱尺寸INCH 最长"></el-table-column>
          <el-table-column width="200px" prop="outboxlenghinch2" label="外箱尺寸INCH 次长"></el-table-column>
          <el-table-column width="200px" prop="outboxshortlenghinch" label="外箱尺寸INCH 最短"></el-table-column>
          <el-table-column width="200px" prop="weightlb1" label="整箱重量LB 净重"></el-table-column>
          <el-table-column width="200px" prop="weightlb2" label="整箱重量LB 毛重"></el-table-column>
          <el-table-column width="200px" prop="quantityhq" label="装柜量"></el-table-column>
          <el-table-column width="200px" prop="quantity" label="一箱几件"></el-table-column>
          <el-table-column width="200px" prop="inboxlenghinch1" label="一箱多件内箱尺寸INCH 最长"></el-table-column>
          <el-table-column width="200px" prop="inboxlenghinch2" label="一箱多件内箱尺寸INCH 次长"></el-table-column>
          <el-table-column width="200px" prop="inboxshortlenghinch" label="一箱多件内箱尺寸INCH 最短"></el-table-column>
          <el-table-column width="200px" prop="inboxweightlb1" label="一箱多件内箱重量LB 净重"></el-table-column>
          <el-table-column width="200px" prop="inboxweightlb2" label="一箱多件内箱重量LB 毛重"></el-table-column>
          <el-table-column width="200px" prop="inboxlenghcm1" label="一箱多件内箱尺寸CM 最长"></el-table-column>
          <el-table-column width="200px" prop="inboxlenghcm2" label="一箱多件内箱尺寸CM 次长"></el-table-column>
          <el-table-column width="200px" prop="inboxshortlenghcm" label="一箱多件内箱尺寸CM 最短"></el-table-column>
          <el-table-column width="200px" prop="inboxweightkg1" label="一箱多件内箱重量KG 净重"></el-table-column>
          <el-table-column width="200px" prop="inboxweightkg2" label="一箱多件内箱重量KG 毛重"></el-table-column>
          <el-table-column width="200px" prop="minlenghinchheight" label="最小包装尺寸INCH 最小包装尺寸长"></el-table-column>
          <el-table-column width="200px" prop="minlenghinchwidth" label="最小包装尺寸INCH 最小包装尺寸宽"></el-table-column>
          <el-table-column width="200px" prop="minlenghinchhigh" label="最小包装尺寸INCH 最小包装尺寸高"></el-table-column>
          <el-table-column width="200px" prop="minweight1" label="最小包装尺寸净重"></el-table-column>
          <el-table-column width="200px" prop="minweight2" label="最小包装尺寸毛重"></el-table-column>
          <el-table-column width="200px" prop="type" label=""></el-table-column>


        </el-table> <!-- 主表单数据展示界面 -->

        <div>
          <el-dialog  title="修改参数" :visible.sync="dislogVisibleUpdate" @close="cancelUpdate" >
            <div><p>填入需要修改的新值 无需修改的字段请空着</p></div>
            <el-input class="el_input1" v-model="updateData.factoryUpdate" placeholder="工厂"></el-input>
            <el-input class="el_input1" v-model="updateData.skuUpdate" placeholder="款号"></el-input>
            <el-input class="el_input1" v-model="updateData.fbaUpdate" placeholder="产品属性FBA"></el-input>
            <el-input class="el_input1" v-model="updateData.fbmUpdate" placeholder="产品属性FBM"></el-input>
            <el-input class="el_input1" v-model="updateData.fulfillmentfeeamazonUpdate" placeholder="亚马逊预估快递费"></el-input>
            <el-input class="el_input1" v-model="updateData.fulfillmentfeeoutseaUpdate" placeholder="海外仓预估快递费(基础运费+附加费)"></el-input>
            <el-input class="el_input1" v-model="updateData.productlenghcm1Update" placeholder="产品尺寸CM 最长"></el-input>
            <el-input class="el_input1" v-model="updateData.productlenghcm2Update" placeholder="产品尺寸CM 次长"></el-input>
            <el-input class="el_input1" v-model="updateData.productshortlenghcmUpdate" placeholder="产品尺寸CM 最短"></el-input>
            <el-input class="el_input1" v-model="updateData.outboxlenghcm1Update" placeholder="外箱尺寸CM 最长"></el-input>
            <el-input class="el_input1" v-model="updateData.outboxlenghcm2Update" placeholder="外箱尺寸CM 次长"></el-input>
            <el-input class="el_input1" v-model="updateData.outboxshortlenghcmUpdate" placeholder="外箱尺寸CM 最短"></el-input>
            <el-input class="el_input1" v-model="updateData.weightkg1Update" placeholder="整箱重量KG 净重"></el-input>
            <el-input class="el_input1" v-model="updateData.weightkg2Update" placeholder="整箱重量KG 毛重"></el-input>
            <el-input class="el_input1" v-model="updateData.productlenghinch1Update" placeholder="产品尺寸INCH 最长"></el-input>
            <el-input class="el_input1" v-model="updateData.productlenghinch2Update" placeholder="产品尺寸INCH 次长"></el-input>
            <el-input class="el_input1" v-model="updateData.productshortlenghinchUpdate" placeholder="产品尺寸INCH 最短"></el-input>
            <el-input class="el_input1" v-model="updateData.outboxlenghinch1Update" placeholder="外箱尺寸INCH 最长"></el-input>
            <el-input class="el_input1" v-model="updateData.outboxlenghinch2Update" placeholder="外箱尺寸INCH 次长"></el-input>
            <el-input class="el_input1" v-model="updateData.outboxshortlenghinchUpdate" placeholder="外箱尺寸INCH 最短"></el-input>
            <el-input class="el_input1" v-model="updateData.weightlb1Update" placeholder="整箱重量LB 净重"></el-input>
            <el-input class="el_input1" v-model="updateData.weightlb2Update" placeholder="整箱重量LB 毛重"></el-input>
            <el-input class="el_input1" v-model="updateData.quantityhqUpdate" placeholder="装柜量"></el-input>
            <el-input class="el_input1" v-model="updateData.quantityUpdate" placeholder="一箱几件"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxlenghinch1Update" placeholder="一箱多件内箱尺寸INCH 最长"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxlenghinch2Update" placeholder="一箱多件内箱尺寸INCH 次长"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxshortlenghinchUpdate" placeholder="一箱多件内箱尺寸INCH 最短"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxweightlb1Update" placeholder="一箱多件内箱重量LB 净重"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxweightlb2Update" placeholder="一箱多件内箱重量LB 毛重"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxlenghcm1Update" placeholder="一箱多件内箱尺寸CM 最长"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxlenghcm2Update" placeholder="一箱多件内箱尺寸CM 次长"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxshortlenghcmUpdate" placeholder="一箱多件内箱尺寸CM 最短"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxweightkg1Update" placeholder="一箱多件内箱重量KG 净重"></el-input>
            <el-input class="el_input1" v-model="updateData.inboxweightkg2Update" placeholder="一箱多件内箱重量KG 毛重"></el-input>
            <el-input class="el_input1" v-model="updateData.minlenghinchheightUpdate" placeholder="最小包装尺寸INCH 最小包装尺寸长"></el-input>
            <el-input class="el_input1" v-model="updateData.minlenghinchwidthUpdate" placeholder="最小包装尺寸INCH 最小包装尺寸宽"></el-input>
            <el-input class="el_input1" v-model="updateData.minlenghinchhighUpdate" placeholder="最小包装尺寸INCH 最小包装尺寸高"></el-input>
            <el-input class="el_input1" v-model="updateData.minweight1Update" placeholder="最小包装尺寸净重"></el-input>
            <el-input class="el_input1" v-model="updateData.minweight2Update" placeholder="最小包装尺寸毛重"></el-input>
            <el-input class="el_input1" v-model="updateData.typeUpdate" placeholder="产品类型"></el-input>
            <p>注:仅支持JPG/PNG且图片大小不得超过4MB</p>
            <p>注:务必分别上传，如果只在一个按钮上传只会上传一张</p>
            <el-upload
                ref="uploadUpdate1"
                class="upload-demo"
                :on-success="handleUploadSuccess"
                :before-upload="beforeUpload"
                :auto-upload="false"
                style="margin-bottom: 20px"
            >
              <el-button slot="trigger" size="small" type="primary">选择产品图片1</el-button>

            </el-upload>
            <el-upload
                ref="uploadUpdate2"
                class="upload-demo"
                :on-success="handleUploadSuccess"
                :before-upload="beforeUpload"
                :auto-upload="false"
                style="margin-bottom: 20px"
            >
              <el-button slot="trigger" size="small" type="primary">选择产品图片2</el-button>

            </el-upload>

            <el-upload
                ref="uploadUpdate3"
                class="upload-demo"
                :on-success="handleUploadSuccess"
                :before-upload="beforeUpload"
                :auto-upload="false"
            >
              <el-button slot="trigger" size="small" type="primary">选择产品图片3</el-button>

            </el-upload>

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
              :page-sizes="[5, 10, 40, 80]"
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
      pageSize:4,
      pageNum:1,
      sku:'',
      factory: '',
      productType:'',
      dialogVisible: false, //添加按钮的弹出
      dislogVisibleUpdate: false,//修改按钮的弹出
      file1: null,
      file2:null,
      newProductAddData:{  //sumbit结尾的变量 标识表单提交数据
        factorySubmit: '',
        skuSubmit: '',
        fbaSubmit: '',
        fbmSubmit: '',
        fulfillmentfeeamazonSubmit: '',
        fulfillmentfeeoutseaSubmit: '',
        productlenghcm1Submit: '',
        productlenghcm2Submit: '',
        productshortlenghcmSubmit: '',
        outboxlenghcm1Submit: '',
        outboxlenghcm2Submit: '',
        outboxshortlenghcmSubmit: '',
        weightkg1Submit: '',
        weightkg2Submit: '',
        productlenghinch1Submit: '',
        productlenghinch2Submit: '',
        productshortlenghinchSubmit: '',
        outboxlenghinch1Submit: '',
        outboxlenghinch2Submit: '',
        outboxshortlenghinchSubmit: '',
        weightlb1Submit: '',
        weightlb2Submit: '',
        quantityhqSubmit: '',
        quantitySubmit: '',
        inboxlenghinch1Submit: '',
        inboxlenghinch2Submit: '',
        inboxshortlenghinchSubmit: '',
        inboxweightlb1Submit: '',
        inboxweightlb2Submit: '',
        inboxlenghcm1Submit: '',
        inboxlenghcm2Submit: '',
        inboxshortlenghcmSubmit: '',
        inboxweightkg1Submit: '',
        inboxweightkg2Submit: '',
        minlenghinchheightSubmit: '',
        minlenghinchwidthSubmit: '',
        minlenghinchhighSubmit: '',
        minweight1Submit: '',
        minweight2Submit: '',
        typeSubmit: '',
      },
      updateData:{
        factoryUpdate: '',
        skuUpdate: '',
        fbaUpdate: '',
        fbmUpdate: '',
        fulfillmentfeeamazonUpdate: '',
        fulfillmentfeeoutseaUpdate: '',
        productlenghcm1Update: '',
        productlenghcm2Update: '',
        productshortlenghcmUpdate: '',
        outboxlenghcm1Update: '',
        outboxlenghcm2Update: '',
        outboxshortlenghcmUpdate: '',
        weightkg1Update: '',
        weightkg2Update: '',
        productlenghinch1Update: '',
        productlenghinch2Update: '',
        productshortlenghinchUpdate: '',
        outboxlenghinch1Update: '',
        outboxlenghinch2Update: '',
        outboxshortlenghinchUpdate: '',
        weightlb1Update: '',
        weightlb2Update: '',
        quantityhqUpdate: '',
        quantityUpdate: '',
        inboxlenghinch1Update: '',
        inboxlenghinch2Update: '',
        inboxshortlenghinchUpdate: '',
        inboxweightlb1Update: '',
        inboxweightlb2Update: '',
        inboxlenghcm1Update: '',
        inboxlenghcm2Update: '',
        inboxshortlenghcmUpdate: '',
        inboxweightkg1Update: '',
        inboxweightkg2Update: '',
        minlenghinchheightUpdate: '',
        minlenghinchwidthUpdate: '',
        minlenghinchhighUpdate: '',
        minweight1Update: '',
        minweight2Update: '',
        typeUpdate: ''
      },
      currentUpdateData:{
        currentFactoryUpdate: '',
        currentSkuUpdate: '',
        currentFbaUpdate: '',
        currentFbmUpdate: '',
        currentFulfillmentfeeamazonUpdate: '',
        currentFulfillmentfeeoutseaUpdate: '',
        currentProductlenghcm1Update: '',
        currentProductlenghcm2Update: '',
        currentProductshortlenghcmUpdate: '',
        currentOutboxlenghcm1Update: '',
        currentOutboxlenghcm2Update: '',
        currentOutboxshortlenghcmUpdate: '',
        currentWeightkg1Update: '',
        currentWeightkg2Update: '',
        currentProductlenghinch1Update: '',
        currentProductlenghinch2Update: '',
        currentProductshortlenghinchUpdate: '',
        currentOutboxlenghinch1Update: '',
        currentOutboxlenghinch2Update: '',
        currentOutboxshortlenghinchUpdate: '',
        currentWeightlb1Update: '',
        currentWeightlb2Update: '',
        currentQuantityhqUpdate: '',
        currentQuantityUpdate: '',
        currentInboxlenghinch1Update: '',
        currentInboxlenghinch2Update: '',
        currentInboxshortlenghinchUpdate: '',
        currentInboxweightlb1Update: '',
        currentInboxweightlb2Update: '',
        currentInboxlenghcm1Update: '',
        currentInboxlenghcm2Update: '',
        currentInboxshortlenghcmUpdate: '',
        currentInboxweightkg1Update: '',
        currentInboxweightkg2Update: '',
        currentMinlenghinchheightUpdate: '',
        currentMinlenghinchwidthUpdate: '',
        currentMinlenghinchhighUpdate: '',
        currentMinweight1Update: '',
        currentMinweight2Update: '',
        currentTypeUpdate: ''
      },
      factoryFilters: [], // 工厂筛选选项
      skuFilters: [], // 款号筛选选项
    }
  },
  watch: {
    productType(newType) {
      this.selectProductType(newType);
    }
  },
  mounted() {
    // 初始化工厂筛选选项
    const factoryOptions = Array.from(new Set(this.tableData.map(item => item.factory)));
    this.factoryFilters = factoryOptions.map(value => ({ text: value, value }));

    // 初始化款号筛选选项
    const skuOptions = Array.from(new Set(this.tableData.map(item => item.sku)));
    this.skuFilters = skuOptions.map(value => ({ text: value, value }));

    // 其他列的筛选选项初始化
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
          "&factory="+
          this.factory +
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
    getUrl(row,imageFlag){
      //console.log(row.url)
      //return  imageFlag===1 ? `/image/${row.url1}` : `/image/${row.url2}`;
      if(imageFlag===1){
        return `/image/${row.url1}`;
      }else if (imageFlag===2){
        return `/image/${row.url2}`;
      }else if (imageFlag===3){
        return `/image/${row.url3}`
      }
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
    cancelForm() {
      this.dialogVisible = false;
      for (let key in this.newProductAddData) {
        this.newProductAddData[key] = '';
      }
      this.$refs.upload1.clearFiles();
      this.$refs.upload2.clearFiles();
      this.$refs.upload3.clearFiles();
    },
    submitForm() {
      // 构造表单数据
      // if (
      //     !this.skuSubmit ||
      //     !this.sku13Submit ||
      //     !this.colorSubmit ||
      //     !this.weight1Submit ||
      //     !this.weight2Submit ||
      //     !this.chicun1Submit ||
      //     !this.chicun2Submit ||
      //     !this.inputSubmit ||
      //     !this.quantitySubmit ||
      //     !this.typeSubmit
      // ) {
      //   this.$message.error('请输入完整的表单信息');
      //   return;
      // }

      // 检查文件是否为空
      if (!this.$refs.upload1.uploadFiles[0] || !this.$refs.upload2.uploadFiles[0] || !this.$refs.upload3.uploadFiles[0]) {
        this.$message.error('请选择产品图片');
        return;
      }

      const formData = new FormData();
      const file1 = this.$refs.upload1.uploadFiles[0];
      const file2 = this.$refs.upload2.uploadFiles[0];
      const file3 = this.$refs.upload3.uploadFiles[0];
      formData.append('file1', file1.raw);
      formData.append('file2', file2.raw);
      formData.append('file3', file3.raw);
      for (let key in this.newProductAddData) {
        if (this.newProductAddData.hasOwnProperty(key)) {
          formData.append(key, this.newProductAddData[key]);
        }
      }
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

          this.currentUpdateData.currentFactoryUpdate = row.factory,
          this.currentUpdateData.currentSkuUpdate = row.sku,
          this.currentUpdateData.currentFbaUpdate = row.fba,
          this.currentUpdateData.currentFbmUpdate = row.fbm,
          this.currentUpdateData.currentFulfillmentfeeamazonUpdate = row.fulfillmentfeeamazon,
          this.currentUpdateData.currentFulfillmentfeeoutseaUpdate = row.fulfillmentfeeoutsea,
          this.currentUpdateData.currentProductlenghcm1Update = row.productlenghcm1,
          this.currentUpdateData.currentProductlenghcm2Update = row.productlenghcm2,
          this.currentUpdateData.currentProductshortlenghcmUpdate = row.productshortlenghcm,
          this.currentUpdateData.currentOutboxlenghcm1Update = row.outboxlenghcm1,
          this.currentUpdateData.currentOutboxlenghcm2Update = row.outboxlenghcm2,
          this.currentUpdateData.currentOutboxshortlenghcmUpdate = row.outboxshortlenghcm,
          this.currentUpdateData.currentWeightkg1Update = row.weightkg1Update,
          this.currentUpdateData.currentWeightkg2Update = row.Weightkg2Update,
          this.currentUpdateData.currentProductlenghinch1Update = row.productlenghinch1,
          this.currentUpdateData.currentProductlenghinch2Update = row.productlenghinch2,
          this.currentUpdateData.currentProductshortlenghinchUpdate = row.productshortlenghinch,
          this.currentUpdateData.currentOutboxlenghinch1Update = row.outboxlenghinch1,
          this.currentUpdateData.currentOutboxlenghinch2Update = row.outboxlenghinch2,
          this.currentUpdateData.currentOutboxshortlenghinchUpdate = row.outboxshortlenghinch,
          this.currentUpdateData.currentWeightlb1Update = row.weightlb1,
          this.currentUpdateData.currentWeightlb2Update = row.weightlb1,
          this.currentUpdateData.currentQuantityhqUpdate = row.quantityhq,
          this.currentUpdateData.currentQuantityUpdate = row.quantity,
          this.currentUpdateData.currentInboxlenghinch1Update =row.inboxlenghinch1,
          this.currentUpdateData.currentInboxlenghinch2Update = row.inboxlenghinch2,
          this.currentUpdateData.currentInboxshortlenghinchUpdate = row.inboxshortlenghinch,
          this.currentUpdateData.currentInboxweightlb1Update = row.inboxweightlb1,
          this.currentUpdateData.currentInboxweightlb2Update = row.inboxweightlb2,
          this.currentUpdateData.currentInboxlenghcm1Update = row.inboxlenghcm1,
          this.currentUpdateData.currentInboxlenghcm2Update = row.inboxlenghcm2,
          this.currentUpdateData.currentInboxshortlenghcmUpdate = row.inboxshortlenghcm,
          this.currentUpdateData.currentInboxweightkg1Update = row.inboxweightkg1,
          this.currentUpdateData.currentInboxweightkg2Update = row.inboxweightkg2,
          this.currentUpdateData.currentMinlenghinchheightUpdate = row.minlenghinchheight,
          this.currentUpdateData.currentMinlenghinchwidthUpdate = row.minlenghinchwidth,
          this.currentUpdateData.currentMinlenghinchhighUpdate = row.minlenghinchhigh,
          this.currentUpdateData.currentMinweight1Update = row.minweight1,
          this.currentUpdateData.currentMinweight2Update = row.minweight2,
          this.currentUpdateData.currentTypeUpdate = row.type



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
      this.$refs.uploadUpdate1.clearFiles();
      this.$refs.uploadUpdate2.clearFiles();
      this.$refs.uploadUpdate3.clearFiles();
    },
    clearFields() {
      // 修改界面每次清空
      for (let key in this.updateData) {
        this.updateData[key] = '';
      }
    },
    saveChanges(id) {   //修改界面提交按钮函数

      //更新前的值 这部分数据是导入到日志数据库的
      const updateProductData = new FormData();
      const fileUpdate1 = this.$refs.uploadUpdate1.uploadFiles[0];
      const fileUpdate2 = this.$refs.uploadUpdate2.uploadFiles[0];
      const fileUpdate3 = this.$refs.uploadUpdate3.uploadFiles[0];

      if (fileUpdate1) {
        updateProductData.append('fileUpdate1', fileUpdate1.raw);
      }

      if (fileUpdate2) {
        updateProductData.append('fileUpdate2', fileUpdate2.raw);
      }

      if (fileUpdate3) {
        updateProductData.append('fileUpdate3', fileUpdate3.raw);
      }
      updateProductData.append('id',this.currentUpdateData.currentUpdateProductId);
      updateProductData.append('factory',this.currentUpdateData.currentFactoryUpdate);
      updateProductData.append('sku',this.currentUpdateData.currentSkuUpdate);
      updateProductData.append('fba',this.currentUpdateData.currentFbaUpdate);
      updateProductData.append('fbm',this.currentUpdateData.currentFbmUpdate);
      updateProductData.append('fulfillmentfeeamazon',this.currentUpdateData.currentFulfillmentfeeamazonUpdate);
      updateProductData.append('fulfillmentfeeoutsea',this.currentUpdateData.currentFulfillmentfeeoutseaUpdate)
      updateProductData.append('productlenghcm1',this.currentUpdateData.currentProductlenghcm1Update)
      updateProductData.append('roductlenghcm2',this.currentUpdateData.currentProductlenghcm2Update)
      updateProductData.append('productshortlenghcm',this.currentUpdateData.currentProductshortlenghcmUpdate)
      updateProductData.append('outboxlenghcm1',this.currentUpdateData.currentOutboxlenghcm1Update)
      updateProductData.append('outboxlenghcm2',this.currentUpdateData.currentOutboxlenghcm2Update)
      updateProductData.append('outboxshortlenghcm',this.currentUpdateData.currentOutboxshortlenghcmUpdate)
      updateProductData.append('weightkg1',this.currentUpdateData.currentWeightkg1Update)
      updateProductData.append('weightkg2',this.currentUpdateData.currentWeightkg2Update)
      updateProductData.append('productlenghinch1',this.currentUpdateData.currentProductlenghinch1Update)
      updateProductData.append('productlenghinch2',this.currentUpdateData.currentProductlenghinch2Update)
      updateProductData.append('productshortlenghinch',this.currentUpdateData.currentProductshortlenghinchUpdate)
      updateProductData.append('outboxlenghinch1',this.currentUpdateData.currentOutboxshortlenghinchUpdate)
      updateProductData.append('outboxlenghinch2',this.currentUpdateData.currentOutboxlenghinch2Update)
      updateProductData.append('outboxshortlenghinch',this.currentUpdateData.currentOutboxshortlenghinchUpdate)
      updateProductData.append('weightlb1',this.currentUpdateData.currentWeightlb1Update)
      updateProductData.append('weightlb2',this.currentUpdateData.currentWeightlb2Update)
      updateProductData.append('quantityhq',this.currentUpdateData.currentQuantityhqUpdate)
      updateProductData.append('quantity',this.currentUpdateData.currentQuantityUpdate)
      updateProductData.append('inboxlenghinch1',this.currentUpdateData.currentInboxlenghinch1Update)
      updateProductData.append('inboxlenghinch2',this.currentUpdateData.currentInboxlenghinch2Update)
      updateProductData.append('inboxshortlenghinch',this.currentUpdateData.currentInboxshortlenghinchUpdate)
      updateProductData.append('inboxweightlb1',this.currentUpdateData.currentInboxweightlb1Update)
      updateProductData.append('inboxweightlb2',this.currentUpdateData.currentInboxweightlb2Update)
      updateProductData.append('inboxlenghcm1',this.currentUpdateData.currentInboxlenghcm1Update)
      updateProductData.append('inboxlenghcm2',this.currentUpdateData.currentInboxlenghcm2Update)
      updateProductData.append('inboxshortlenghcm',this.currentUpdateData.currentInboxshortlenghcmUpdate)
      updateProductData.append('inboxweightkg1',this.currentUpdateData.currentInboxweightkg1Update)
      updateProductData.append('inboxweightkg2',this.currentUpdateData.currentInboxweightkg2Update)
      updateProductData.append('minlenghinchheight',this.currentUpdateData.currentMinlenghinchheightUpdate)
      updateProductData.append('minlenghinchwidth',this.currentUpdateData.currentMinlenghinchwidthUpdate)
      updateProductData.append('minlenghinchhigh',this.currentUpdateData.currentMinlenghinchhighUpdate)
      updateProductData.append('minweight1',this.currentUpdateData.currentMinweight1Update)
      updateProductData.append('minweight2',this.currentUpdateData.currentMinweight2Update)
      updateProductData.append('type',this.currentUpdateData)

      //这是新的数据 覆盖到数据库里的
      updateProductData.append('factoryUpdate', this.updateData.factoryUpdate);
      updateProductData.append('skuUpdate', this.updateData.skuUpdate);
      updateProductData.append('fbaUpdate', this.updateData.fbaUpdate);
      updateProductData.append('fbmUpdate', this.updateData.fbmUpdate);
      updateProductData.append('fulfillmentfeeamazonUpdate', this.updateData.fulfillmentfeeamazonUpdate);
      updateProductData.append('fulfillmentfeeoutseaUpdate', this.updateData.fulfillmentfeeoutseaUpdate);
      updateProductData.append('productlenghcm1Update', this.updateData.productlenghcm1Update);
      updateProductData.append('productlenghcm2Update', this.updateData.productlenghcm2Update);
      updateProductData.append('productshortlenghcmUpdate', this.updateData.productshortlenghcmUpdate);
      updateProductData.append('outboxlenghcm1Update', this.updateData.outboxlenghcm1Update);
      updateProductData.append('outboxlenghcm2Update', this.updateData.outboxlenghcm2Update);
      updateProductData.append('outboxshortlenghcmUpdate', this.updateData.outboxshortlenghcmUpdate);
      updateProductData.append('weightkg1Update', this.updateData.weightkg1Update);
      updateProductData.append('weightkg2Update', this.updateData.weightkg2Update);
      updateProductData.append('productlenghinch1Update', this.updateData.productlenghinch1Update);
      updateProductData.append('productlenghinch2Update', this.updateData.productlenghinch2Update);
      updateProductData.append('productshortlenghinchUpdate', this.updateData.productshortlenghinchUpdate);
      updateProductData.append('outboxlenghinch1Update', this.updateData.outboxlenghinch1Update);
      updateProductData.append('outboxlenghinch2Update', this.updateData.outboxlenghinch2Update);
      updateProductData.append('outboxshortlenghinchUpdate', this.updateData.outboxshortlenghinchUpdate);
      updateProductData.append('weightlb1Update', this.updateData.weightlb1Update);
      updateProductData.append('weightlb2Update', this.updateData.weightlb2Update);
      updateProductData.append('quantityhqUpdate', this.updateData.quantityhqUpdate);
      updateProductData.append('quantityUpdate', this.updateData.quantityUpdate);
      updateProductData.append('inboxlenghinch1Update', this.updateData.inboxlenghinch1Update);
      updateProductData.append('inboxlenghinch2Update', this.updateData.inboxlenghinch2Update);
      updateProductData.append('inboxshortlenghinchUpdate', this.updateData.inboxshortlenghinchUpdate);
      updateProductData.append('inboxweightlb1Update', this.updateData.inboxweightlb1Update);
      updateProductData.append('inboxweightlb2Update', this.updateData.inboxweightlb2Update);
      updateProductData.append('inboxlenghcm1Update', this.updateData.inboxlenghcm1Update);
      updateProductData.append('inboxlenghcm2Update', this.updateData.inboxlenghcm2Update);
      updateProductData.append('inboxshortlenghcmUpdate', this.updateData.inboxshortlenghcmUpdate);
      updateProductData.append('inboxweightkg1Update', this.updateData.inboxweightkg1Update);
      updateProductData.append('inboxweightkg2Update', this.updateData.inboxweightkg2Update);
      updateProductData.append('minlenghinchheightUpdate', this.updateData.minlenghinchheightUpdate);
      updateProductData.append('minlenghinchwidthUpdate', this.updateData.minlenghinchwidthUpdate);
      updateProductData.append('minlenghinchhighUpdate', this.updateData.minlenghinchhighUpdate);
      updateProductData.append('minweight1Update', this.updateData.minweight1Update);
      updateProductData.append('minweight2Update', this.updateData.minweight2Update);
      updateProductData.append('typeUpdate', this.updateData.typeUpdate);

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
      //console.log("我点击了切换界面按钮")
      this.$router.push('/orderDemo');
    },
    supplierPage(){
      this.$router.push('/supplier');
    },
    filterMethod(value, row, column) {
      const prop = column.property;
      return row[prop].toLowerCase().includes(value.toLowerCase());
    },

  }

}
</script>
