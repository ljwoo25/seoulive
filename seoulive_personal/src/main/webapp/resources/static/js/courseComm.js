console.log("Comment Module .....");

var commService = (function() {

    function add(comm, callback, error) {
        console.log("add comment .......");

        $.ajax({
            type:'post',
            url: '/replies/new',
            data: JSON.stringify(comm),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if(callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error) {
                    error(er);
                }

            }
        })
    }
    function getList(param, callback, error) {
        var seq = param.seq;
        var page = param.page || 1;
        var board_name = param.board_name;

        $.getJSON("/replies/pages/" + board_name + "/" + page + ".json",
            function (data) {
                if (callback) {
                    callback(data);
                }
            }).fail(function (xhr, status, err) {
                if(error) {
                    error();
                }
        });

    }
    function remove(seq, callback, error) {
        $.ajax({
            type: 'delete',
            url: '/replies/' + seq,
            success: function (deleteResult, status, xhr) {
                if(callback) {
                    callback(deleteResult);
                }
            },
            error: function (xhr, status, er) {
                if(error) {
                    error(er);
                }
            }
        })

    }
    function update(content, callback, error) {
        console.log("SEQ: ", content.seq);

        $.ajax({
            type: 'put',
            url: '/replies/' + content.seq,
            data: JSON.stringify(content),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if(callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error) {
                    error(er);
                }
            }
        });
    }

    return {
        add: add,
        getList: getList,
        remove: remove,
        update: update
        };
    })();