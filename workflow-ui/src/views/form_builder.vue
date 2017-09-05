<template>
    <div class="content">
      <div id="fb-editor" class="build-wrap"></div>
      <form id ="render-form" class="render-wrap"></form>
      <button id="edit-form" style="background-color: #afd9ee" >Edit Form</button>
      <br><br>
      <button id="copy-html" style="background-color: #c7ddef" data-clipboard-target="render-form">Copy Html</button>
      <input type="hidden" id="code" :value = "code">
      <input type="hidden" id="name" :value = "name">
    </div>
</template>
<script>

  import '../assets/formBuilder/jquery-ui.min'
  import '../assets/formBuilder/jquery.min'
  import '../assets/formBuilder/js/form-builder.min'
  import '../assets/formBuilder/js/form-render.min'
  //import '../assets/formBuilder/js/vendor'
  import '../assets/formBuilder/js/ZeroClipboard'
  import '../assets/formBuilder/css/builder.css'

  export default{
    name: '',
    data(){
      return {
        code: this.$route.query.code,
        name: this.$route.query.name,
        json: this.$route.query.json
      }
    },
    methods: {
      loadFormList(){
        this.$router.push('/form_list');
      }
    },

    mounted: function () {
      var fields = [
        //自定义表单元素
        {
          type: 'autocomplete',
          label: 'Custom Autocomplete',
          required: true,
          values: [
            {label: 'SQL'},
            {label: 'C#'},
            {label: 'JavaScript'},
            {label: 'Java'},
            {label: 'Python'},
            {label: 'C++'},
            {label: 'PHP'},
            {label: 'Swift'},
            {label: 'Ruby'}
          ]
        },

      ];

      var templates = {
        starRating: function(fieldData) {
          return {
            field: '<span id="'+fieldData.name+'">',
            onRender: function() {
              $(document.getElementById(fieldData.name)).rateYo({rating: 3.6});
            }
          };
        }
      };

      var inputSets = [{
        label: 'User Details',
        name: 'user-details', // optional
        showHeader: true, // optional
        fields: [{
          type: 'text',
          label: 'First Name',
          className: 'form-control'
        }, {
          type: 'select',
          label: 'Profession',
          className: 'form-control',
          values: [{
            label: 'Street Sweeper',
            value: 'option-2',
            selected: false
          }, {
            label: 'Brain Surgeon',
            value: 'option-3',
            selected: false
          }]
        }, {
          type: 'textarea',
          label: 'Short Bio:',
          className: 'form-control'
        }]
      }, {
        label: 'User Agreement',
        fields: [{
          type: 'header',
          subtype: 'h3',
          label: 'Terms & Conditions',
          className: 'header'
        }, {
          type: 'paragraph',
          label: 'Leverage agile frameworks to provide a robust synopsis for high level overviews. Iterative approaches to corporate strategy foster collaborative thinking to further the overall value proposition. Organically grow the holistic world view of disruptive innovation via workplace diversity and empowerment.',
        }, {
          type: 'paragraph',
          label: 'Bring to the table win-win survival strategies to ensure proactive domination. At the end of the day, going forward, a new normal that has evolved from generation X is on the runway heading towards a streamlined cloud solution. User generated content in real-time will have multiple touchpoints for offshoring.',
        }, {
          type: 'checkbox',
          label: 'Do you agree to the terms and conditions?',
        }]
      }];

      var typeUserDisabledAttrs = {
        autocomplete: ['access']
      };

      var typeUserAttrs = {
        text: {
          className: {
            label: 'Class',
            options: {
              'red form-control': 'Red',
              'green form-control': 'Green',
              'blue form-control': 'Blue'
            },
            style: 'border: 1px solid red'
          }
        }
      };

      var actionButtons = [
        {
          id: "save",
          className: "btn btn-success",
          label: "Save",
          type: "button",
          events: {
            click: function() {
              //保存数据
              var jsonData = formBuilder.actions.getData('json', true);
              var code = $("#code").val();
              var name = $("#name").val();
              var url = global.HOST + '/form/save/'+code + '/' + name;
              $.ajax({
                type: "post",
                url: url,
                async : false,
                data: { param : jsonData},
                dataType: "json",
                success: function (data) {
                  //刷新頁面
                  window.location.href = '/form_list';
                }
              });
            }
          }
        }
      ];

      var fbOptions = {
        subtypes: {
          text: ['datetime-local']
        },
        onSave: function(e, formData) {
          toggleEdit();
          $('.render-wrap').formRender({
            formData: formData,
            templates: templates
          });
          window.sessionStorage.setItem('formData', JSON.stringify(formData));
        },
        stickyControls: {
          enable: true
        },
        sortableControls: true,
        fields: fields,
        inputSets: inputSets,
        typeUserDisabledAttrs: typeUserDisabledAttrs,
        typeUserAttrs: typeUserAttrs,
        disableInjectedStyle: false,
        disableFields: ['autocomplete'],
        actionButtons : actionButtons,
        controlOrder: ['header','text','textarea','select','number','date',
          'checkbox-group','radio-group','hidden','file','paragraph'],
        i18n: {
          locale: 'en-US'
        }
      };
      var formData = window.sessionStorage.getItem('formData');
      var editing = true;

//load 上一次编辑的数据
//      if (formData) {
//        fbOptions.formData = JSON.parse(formData);
//      }

      /**
       * Toggles the edit mode for the demo
       * @return {Boolean} editMode
       */
      function toggleEdit() {
        document.body.classList.toggle('form-rendered', editing);
        return editing = !editing;
      }

      var setFormData = '[{"type":"text","label":"Full Name","subtype":"text","className":"form-control","name":"text-1476748004559"},{"type":"select","label":"Occupation","className":"form-control","name":"select-1476748006618","values":[{"label":"Street Sweeper","value":"option-1","selected":true},{"label":"Moth Man","value":"option-2"},{"label":"Chemist","value":"option-3"}]},{"type":"textarea","label":"Short Bio","rows":"5","className":"form-control","name":"textarea-1476748007461"}]';

      var formBuilder = $('.build-wrap').formBuilder(fbOptions);

      var fbPromise = formBuilder.promise;
      fbPromise.then(function(fb) {
        var apiBtns = {
          showData: fb.actions.showData,
          clearFields: fb.actions.clearFields,
          getData: function() {
            console.log(fb.actions.getData());
          },
          setData: function() {
            fb.actions.setData(setFormData);
          },
          addField: function() {
            var field = {
              type: 'text',
              class: 'form-control',
              label: 'Text Field added at: ' + new Date().getTime()
            };
            fb.actions.addField(field);
          },
          removeField: function() {
            fb.actions.removeField();
          },
          testSubmit: function() {
            var formData = new FormData(document.forms[0]);
            console.log('Can submit: ', document.forms[0].checkValidity());
            // Display the key/value pairs
            console.log('FormData: ', );
            for(var pair of formData.entries()) {
              console.log(pair[0]+ ': '+ pair[1]);
            }
          },
          resetDemo: function() {
            window.sessionStorage.removeItem('formData');
            location.reload();
          }
        };
      });
      document.getElementById('edit-form').onclick = function() {
        toggleEdit();
      };
      document.getElementById('copy-html').onclick = function() {
        var text = document.getElementById('render-form').outerHTML;
//        // 定义一个新的复制对象
//        var clip = new ZeroClipboard( document.getElementById("copy-html"));
//        clip.setHtml(text);
          alert("请复制以下内容到剪切板   " + text);
      };

      function copyToClipboard(){
        var mall = {
          text: text
        };
        var url = global.HOST + '/form/copyToClipboard';
        alert(mall);
        $.ajax({
          type: "post",
          url: url,
          async : false,
          data: { param : JSON.stringify(mall)},
          dataType: "json",
          success: function (data) {
            alert("复制成功");
          }
        });
      }

      this.$store.commit('changeBreadcrumbData', [{
        name: '首页',
        to: '/'
      }, {
        name: '表单列表',
        to: '/form_list'
      }, {
        name: '表单设计 :  ' + this.$route.query.name,
        to: '#'
      }]);
    },
  }
</script>
<style>

</style>
