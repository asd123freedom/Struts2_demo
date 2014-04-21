(function($) {       
	$.fn.MyUpload = function() {
	     var _this=$(this);
	     _this.data("total",0);
	     _this.data("loaded",0);
	     _this.bind("dragenter",function(e){
	    	 e.stopPropagation();
	    	 e.preventDefault();
	     });
	     _this.bind("dragover",function(e){
	    	 e.originalEvent.dataTransfer.dropEffect="copy";
	    	 e.stopPropagation();
	    	 e.preventDefault();
	     });

	     var Utilty={
	    	 _upload:function upload(file){
			    	 var form = new FormData();
			    	 console.log(file.size);
			    	 var dfd=$.Deferred();
					 form.append("grand",file,file.name);
					 form.append("relativePath",file.relativePath);
					 form.append("folderName","test");
					 // XMLHttpRequest 对象
					 var xhr = new XMLHttpRequest();
					 xhr.open("post", "uploadFile.action", true);
					 xhr.onload = function() {
						 //console.log("上传完成!");
						 //$(".modal.oper").modal("hide");
						 //$(".btn.fresh").trigger("click");
						 var total=_this.data("total");
						 var loaded=_this.data("loaded");
						 loaded+=file.size;
						 _this.data("loaded",loaded);
						 var progress = parseInt(loaded / total * 100, 10);
		       			 $('.progress .bar').css(
		            			'width',
		           				 progress + '%'
		        		 );
						 dfd.resolve();
					 };
					 var obj=xhr.upload;					 
					 $(obj).bind("progress",function(e){
						 e=e.originalEvent;
						 var total=_this.data("total");
						 var loaded=_this.data("loaded");
						 var position=e.position+loaded;
						 _this.data("loaded",position);
						 var progress = parseInt(e.position / total * 100, 10);
		       			 $('.progress .bar').css(
		            			'width',
		           				 progress + '%'
		        		 );
					 });
					 console.log(_this.data("total"));
					xhr.send(form);
					return dfd.promise();
			     },
	    	 _handleFileTreeEntry: function (entry, path) {
		            var that = this,
		                dfd = $.Deferred(),
		                errorHandler = function (e) {
		                    if (e && !e.entry) {
		                        e.entry = entry;
		                    }
		                    // Since $.when returns immediately if one
		                    // Deferred is rejected, we use resolve instead.
		                    // This allows valid files and invalid items
		                    // to be returned together in one set:
		                    dfd.resolve([e]);
		                },
		                dirReader;
		            path = path || '';
		            if (entry.isFile) {
		                if (entry._file) {
		                    // Workaround for Chrome bug #149735
		                    entry._file.relativePath = path;
		                    var size=_this.data("total");
		                    size+=entry._file.size;
		                    _this.data("total",size);
		                    dfd.resolve(entry._file);
		                } else {
		                    entry.file(function (file) {
		                        file.relativePath = path;
		                        var size=_this.data("total");
		                    	size+=file.size;
		                    	_this.data("total",size);
		                        dfd.resolve(file);
		                    }, errorHandler);
		                }
		            } else if (entry.isDirectory) {
		                dirReader = entry.createReader();
		                dirReader.readEntries(function (entries) {
		                    that._handleFileTreeEntries(
		                        entries,
		                        path + entry.name + '/'
		                    ).done(function (files) {
		                        dfd.resolve(files);
		                    }).fail(errorHandler);
		                }, errorHandler);
		            } else {
		                // Return an empy list for file system items
		                // other than files or directories:
		                dfd.resolve([]);
		            }
		            return dfd.promise();
		        },
		
		        _handleFileTreeEntries: function (entries, path) {
		            var that = this;
		            return $.when.apply(
		                $,
		                $.map(entries, function (entry) {
		                    return that._handleFileTreeEntry(entry, path);
		                })
		            ).pipe(function () {
		                return Array.prototype.concat.apply(
		                    [],
		                    arguments
		                );
		            });
		        },
		        _getDroppedFiles: function (dataTransfer) {
		            dataTransfer = dataTransfer || {};
		            var items = dataTransfer.items;
		            if (items && items.length && (items[0].webkitGetAsEntry ||
		                    items[0].getAsEntry)) {
		                return this._handleFileTreeEntries(
		                    $.map(items, function (item) {
		                        var entry;
		                        if (item.webkitGetAsEntry) {
		                            entry = item.webkitGetAsEntry();
		                            if (entry) {
		                                // Workaround for Chrome bug #149735:
		                                entry._file = item.getAsFile();
		                            }
		                            return entry;
		                        }
		                        return item.getAsEntry();
		                    })
		                );
		            }
		            return $.Deferred().resolve(
		                $.makeArray(dataTransfer.files)
		            ).promise();
		        },
	     };
	     _this.bind("drop",function(e){
	    	 e.stopPropagation();
	    	 e.preventDefault();
	    	 e.dataTransfer = e.originalEvent && e.originalEvent.dataTransfer;
            var that = this,
                dataTransfer = e.dataTransfer,
                data = {};
            //console.log(dataTransfer.files);
            if (dataTransfer && dataTransfer.files && dataTransfer.files.length) {
                e.preventDefault();
                Utilty._getDroppedFiles(dataTransfer).always(function (files) {
                    data.files = files;
                    console.log(files);
                    //等所有上传工作完成之后再弹出上传成功的消息
                    $.when.apply(
		                $,
		                $.map(files,function(file){
		                	 Utilty._upload(file);
		                })).done(function(){
		                	//alert("上传成功");
		                });
                    
                });
            }
	     });
	};     
})(jQuery); 